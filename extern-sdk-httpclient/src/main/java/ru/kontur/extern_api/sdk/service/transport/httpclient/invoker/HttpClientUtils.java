/*
 * The MIT License
 *
 * Copyright 2018 alexs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ru.kontur.extern_api.sdk.service.transport.httpclient.invoker;

import static com.google.gson.internal.bind.util.ISO8601Utils.format;

import com.google.gson.internal.bind.util.ISO8601Utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alexs
 */
public class HttpClientUtils {

    private static final DateFormat[] supportedFormates = new DateFormat[] {
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'")
    };

    private static final DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    /**
     * Format the given Date object into string (Datetime format).
     *
     * @param date Date object
     * @return Formatted datetime in string representation
     */
    public static String formatDatetime(Date date) {
        return outputFormat.format(date);
    }

    public static Date parseDateTime(String date) {
        try {
            return ISO8601Utils.parse(date, new ParsePosition(0));
        } catch (ParseException ignored) {
            // Ok, try to use other date formatters
        }

        for (DateFormat supportedFormate : supportedFormates) {
            try {
                return supportedFormate.parse(date);
            } catch (ParseException ignored) {
                // maybe later?
            }
        }

        throw new RuntimeException(new ParseException(date, 0));
    }

}
