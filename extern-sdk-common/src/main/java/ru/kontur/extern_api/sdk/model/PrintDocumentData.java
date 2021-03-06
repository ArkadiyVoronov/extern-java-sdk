/*
 * MIT License
 *
 * Copyright (c) 2018 SKB Kontur
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package ru.kontur.extern_api.sdk.model;

import com.google.gson.annotations.SerializedName;

/**
 * <p>Класс контент печатной формы документа в PDF формате в кодировке BASE64</p>
 * @author Mikhail Pavlenko
 */

public class PrintDocumentData {
    @SerializedName("content")
    private String content = null;

    public PrintDocumentData content(String content) {
        this.content = content;
        return this;
    }

    /**
     * Возвращает контент печатной формы документа в PDF формате в кодировке BASE64
     * @return контент печатной формы документа в PDF формате в кодировке BASE64
     */
    public String getContent() {
        return content;
    }

    /**
     * Устанавливает контент печатной формы документа в PDF формате в кодировке BASE64
     * @param content контент печатной формы документа в PDF формате в кодировке BASE64
     */
    public void setContent(String content) {
        this.content = content;
    }
}
