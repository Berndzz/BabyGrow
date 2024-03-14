package com.hardus.babygrow.util.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun makeBold(text: String, boldText: String): AnnotatedString {
    val startIndex = text.indexOf(boldText)
    if (startIndex == -1) {
        return AnnotatedString(text)
    }
    val endIndex = startIndex + boldText.length

    return buildAnnotatedString {
        append(text)
        addStyle(
            style = SpanStyle(
                color = Color.Black, // warna teks
                fontWeight = FontWeight.Bold // teks bold
            ),
            start = startIndex,
            end = endIndex
        )
    }
}

@Composable
fun makeMultipleBold(text: String, boldTextList: List<String>): AnnotatedString {
    var currentStartIndex = 0
    return buildAnnotatedString {
        boldTextList.forEach { boldText ->
            val startIndex = text.indexOf(boldText, currentStartIndex)
            if (startIndex == -1) {
                append(text.substring(currentStartIndex))
                return@buildAnnotatedString
            }
            val endIndex = startIndex + boldText.length

            append(text.substring(currentStartIndex, startIndex))
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(text.substring(startIndex, endIndex))
            }
            currentStartIndex = endIndex
        }
        append(text.substring(currentStartIndex))
    }
}

@Composable
fun makeMultipleBold1(text: String, boldTextList: List<String>): AnnotatedString {
    val annotatedString = buildAnnotatedString {
        var currentStartIndex = 0
        boldTextList.forEach { boldText ->
            val startIndex = text.indexOf(boldText, currentStartIndex)
            if (startIndex == -1) {
                return@forEach
            }
            val endIndex = startIndex + boldText.length

            append(text.substring(currentStartIndex, startIndex))
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(text.substring(startIndex, endIndex))
            }
            currentStartIndex = endIndex
        }
        append(text.substring(currentStartIndex))
    }

    // Handle case where no text is made bold
    if (annotatedString.text == text) {
        return AnnotatedString(text)
    }

    return annotatedString
}
