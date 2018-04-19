/*
 * MIT License
 *
 * Copyright (c) 2018 SKB Kontur
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ru.skbkontur.sdk.extern.service.transport.adaptors.dto;

import ru.skbkontur.sdk.extern.model.DocflowPageItem;

import java.util.stream.Collectors;


/**
 * @author alexs
 */
public class DocflowPageItemDto {

    public DocflowPageItem fromDto(ru.skbkontur.sdk.extern.service.transport.swagger.model.DocflowPageItem dto) {

        if (dto == null) return null;

        DocflowPageItem docflowPageItem = new DocflowPageItem();

        LinkDto linkDto = new LinkDto();

        docflowPageItem.setId(dto.getId());
        docflowPageItem.setLastChangeDate(dto.getLastChangeDate());
        docflowPageItem.setLinks(dto.getLinks().stream().map(linkDto::fromDto).collect(Collectors.toList()));
        docflowPageItem.setSendDate(dto.getSendDate());
        docflowPageItem.setStatus(dto.getStatus());
        docflowPageItem.setType(dto.getType());

        return docflowPageItem;
    }

    public ru.skbkontur.sdk.extern.service.transport.swagger.model.DocflowPageItem toDto(DocflowPageItem docflowPageItem) {

        if (docflowPageItem == null) return null;

        ru.skbkontur.sdk.extern.service.transport.swagger.model.DocflowPageItem dto
                = new ru.skbkontur.sdk.extern.service.transport.swagger.model.DocflowPageItem();

        LinkDto linkDto = new LinkDto();

        dto.setId(docflowPageItem.getId());
        dto.setLastChangeDate(docflowPageItem.getLastChangeDate());
        dto.setLinks(docflowPageItem.getLinks().stream().map(linkDto::toDto).collect(Collectors.toList()));
        dto.setSendDate(docflowPageItem.getSendDate());
        dto.setStatus(docflowPageItem.getStatus());
        dto.setType(docflowPageItem.getType());

        return dto;
    }
}