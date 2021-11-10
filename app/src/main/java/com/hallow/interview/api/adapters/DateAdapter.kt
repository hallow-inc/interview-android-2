package com.hallow.interview.api.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

internal class DateAdapter : JsonSerializer<Date>, JsonDeserializer<Date> {

    override fun serialize(src: Date, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val dateFormatAsString = iso8601DateFormat.format(src)
        return JsonPrimitive(dateFormatAsString)
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date {
        if (json !is JsonPrimitive) {
            throw JsonParseException("The date should be a string value")
        }
        return deserializeToDate(json)?.let {
            return it
        } ?: run {
            throw IllegalArgumentException("$javaClass cannot deserialize to $typeOfT")
        }
    }

    private fun deserializeToDate(json: JsonElement): Date? {
        try {
            return iso8601DateFormat.parse(json.asString)
        } catch (e: ParseException) {
            throw JsonSyntaxException(json.asString, e)
        }
    }

    companion object {
        val iso8601DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    }
}
