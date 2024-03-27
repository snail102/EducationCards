package ru.anydevprojects.educationcards.importDeck.data

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anydevprojects.educationcards.database.dao.CardDao
import ru.anydevprojects.educationcards.database.dao.DeckDao
import ru.anydevprojects.educationcards.database.models.CardEntity
import ru.anydevprojects.educationcards.database.models.DeckEntity
import ru.anydevprojects.educationcards.importDeck.data.models.CardFromDb
import ru.anydevprojects.educationcards.importDeck.data.models.DataFromDb
import ru.anydevprojects.educationcards.importDeck.domain.ImportDeckRepository

class ImportDeckRepositoryImpl(
    private val applicationContext: Context,
    private val deckDao: DeckDao,
    private val cardDao: CardDao
) : ImportDeckRepository {

    override suspend fun importDeckFromFile(uri: Uri): Result<Unit> {
        val fileName = getFileNameFromUri(uri)

        if (!isCorrectFileExtension(fileName)) {
            return Result.failure(Exception("Incorrect file path"))
        }

        if (isDirectoryExists(
                "${applicationContext.filesDir.absolutePath}/${
                    getNameFileFromPath(
                        fileName
                    )
                }"
            )
        ) {
            Log.d(
                "isDirectoryExists",
                "${applicationContext.filesDir.absolutePath}/${getNameFileFromPath(fileName)}"
            )
            return Result.failure(Exception("directory is exists"))
        }

        val filePath = getFilePathFromUri(uri)
        Log.d("filePath", filePath.toString())
        if (filePath == null) {
            return Result.failure(Exception("Incorrect file path"))
        }

        withContext(Dispatchers.IO) {
            val files = unzipFiles(filePath)

            val dbFilePath = getPathDatabaseFile(files)
            val db = SQLiteDatabase.openDatabase(
                dbFilePath,
                null,
                SQLiteDatabase.OPEN_READONLY
            )
            val dataList = mutableListOf<CardFromDb>()
            try {
                val cursor: Cursor = db.rawQuery("SELECT * FROM notes", null)

                while (cursor.moveToNext()) {
                    val value = cursor.getString(cursor.getColumnIndex("flds"))
                    val separatedValue = value.split("\u001F")
                    val front = separatedValue.getOrNull(0)
                    val back = separatedValue.getOrNull(1)
                    if (front != null) {
                        dataList.add(
                            CardFromDb(front = front, back = back.orEmpty())
                        )
                    }
                }

                cursor.close()
            } catch (e: SQLiteException) {
                // Обработка ошибок при работе с базой данных
                e.printStackTrace()
            }

            val dataFromDb = DataFromDb(
                name = getNameFileFromPath(filePath),
                path = filePath,
                cardList = dataList
            )
            val deckId = UUID.randomUUID().toString()
            val deckEntity = DeckEntity(
                uid = deckId,
                name = dataFromDb.name,
                path = dataFromDb.path
            )
            deckDao.insert(deckEntity)

            cardDao.insertAll(
                dataFromDb.cardList.map {
                    CardEntity(
                        uid = UUID.randomUUID().toString(),
                        deckId = deckId,
                        front = it.front,
                        back = it.back
                    )
                }
            )
        }
        Log.d("fileName", "file name: $fileName from uri: $uri")

        return Result.success(Unit)
    }

    @SuppressLint("Range")
    private fun getFilePathFromUri(uri: Uri): String? {
        val contentResolver: ContentResolver = applicationContext.contentResolver
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)

        cursor?.use {
            if (it.moveToFirst()) {
                val displayName = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                val inputStream: InputStream? = contentResolver.openInputStream(uri)

                if (inputStream != null) {
                    try {
                        val file = File(applicationContext.cacheDir, displayName)
                        copyInputStreamToFile(inputStream, file)
                        return file.absolutePath
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } finally {
                        inputStream.close()
                    }
                }
            }
        }

        return null
    }

    private fun copyInputStreamToFile(inputStream: InputStream, file: File) {
        val outputStream = FileOutputStream(file)
        try {
            val buffer = ByteArray(4 * 1024)
            var read: Int
            while (inputStream.read(buffer).also { read = it } != -1) {
                outputStream.write(buffer, 0, read)
            }
            outputStream.flush()
        } finally {
            outputStream.close()
        }
    }

    private fun unzipFiles(pathArchive: String): String {
        val unzipDirectoryPath = "${applicationContext.filesDir.absolutePath}/${
            getNameFileFromPath(
                pathArchive
            )
        }"
        //    Files.createDirectory(Paths.get(URI(unzipDirectoryPath)))
//        unzip(
//            zipFilePath = pathArchive,
//            destinationDirectory = unzipDirectoryPath
//        )
        UnzipUtils.unzip(File(pathArchive), unzipDirectoryPath)
        return unzipDirectoryPath
    }

    private fun getPathDatabaseFile(pathImportsFilesDirectory: String): String {
        return findFilesByExtension(
            directory = File(pathImportsFilesDirectory),
            extension = "anki21"
        ).firstOrNull()?.absolutePath ?: findFilesByExtension(
            directory = File(pathImportsFilesDirectory),
            extension = "anki2"
        ).firstOrNull()?.absolutePath.orEmpty()
    }

    private fun findFilesByExtension(directory: File, extension: String): List<File> {
        val resultFiles = mutableListOf<File>()

        if (directory.isDirectory) {
            val files = directory.listFiles()
            files?.let {
                for (file in it) {
                    if (file.isFile && file.extension == extension) {
                        resultFiles.add(file)
                    }
                }
            }
        }

        return resultFiles
    }

    @SuppressLint("Range")
    private fun getFileNameFromUri(uri: Uri): String {
        var fileName = ""
        applicationContext.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            }
        }
        return fileName
    }

    private fun getNameFileFromPath(path: String): String {
        return path.split("/").lastOrNull()?.substringBefore(".").orEmpty()
    }

    private fun isCorrectFileExtension(fileName: String): Boolean {
        return fileName.contains(".apkg")
    }

    fun isDirectoryExists(directoryPath: String): Boolean {
        val directory = File(directoryPath)
        return directory.exists() && directory.isDirectory
    }
}
