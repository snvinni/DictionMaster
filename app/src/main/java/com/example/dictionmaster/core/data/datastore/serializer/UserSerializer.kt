package com.example.dictionmaster.core.data.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.dictionmaster.core.data.datastore.proto.user.DataUser
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<DataUser> {

    override val defaultValue: DataUser = DataUser.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): DataUser {
        return try {
            DataUser.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: DataUser,
        output: OutputStream,
    ) {
        t.writeTo(output)
    }
}