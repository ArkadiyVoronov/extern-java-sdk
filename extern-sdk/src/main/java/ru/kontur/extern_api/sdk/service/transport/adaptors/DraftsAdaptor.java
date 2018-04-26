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

package ru.kontur.extern_api.sdk.service.transport.adaptors;

import ru.kontur.extern_api.sdk.model.Docflow;
import ru.kontur.extern_api.sdk.model.DraftDocument;
import ru.kontur.extern_api.sdk.service.transport.adaptors.dto.DocflowDto;
import ru.kontur.extern_api.sdk.service.transport.adaptors.dto.DocumentContentsDto;
import ru.kontur.extern_api.sdk.service.transport.adaptors.dto.DraftDocumentDto;
import ru.kontur.extern_api.sdk.service.transport.adaptors.dto.DraftDto;
import ru.kontur.extern_api.sdk.service.transport.adaptors.dto.DraftMetaDto;
import ru.kontur.extern_api.sdk.service.transport.adaptors.dto.PrepareResultDto;
import ru.kontur.extern_api.sdk.service.transport.adaptors.dto.UsnServiceContractInfoDto;
import ru.kontur.extern_api.sdk.service.transport.adaptors.dto.UsnServiceContractInfoV2Dto;
import ru.kontur.extern_api.sdk.service.transport.invoker.ApiClient;
import ru.kontur.extern_api.sdk.service.transport.swagger.api.DraftsApi;
import ru.kontur.extern_api.sdk.service.transport.swagger.invoker.ApiException;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.kontur.extern_api.sdk.service.transport.adaptors.QueryContext.*;


/**
 * @author AlexS
 */
public class DraftsAdaptor extends BaseAdaptor {

    private final DraftsApi api;

    public DraftsAdaptor() {
        this(new DraftsApi());
    }

    public DraftsAdaptor(DraftsApi api) {
        this.api = api;
    }

    @Override
    public ApiClient getApiClient() {
        return (ApiClient) api.getApiClient();
    }

    @Override
    public void setApiClient(ApiClient apiClient) {
        api.setApiClient(apiClient);
    }

    /**
     * Create new a draft
     * <p>
     * POST /v1/{billingAccountId}/drafts
     *
     * @param cxt a context
     * @return QueryContext &lt;UUID&gt;
     */
    public QueryContext<UUID> createDraft(QueryContext<UUID> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            UUID draftId =
                    transport(cxt)
                            .draftsCreate(
                                    cxt.getAccountProvider().accountId(),
                                    new DraftMetaDto().toDto(cxt.getDraftMeta())
                            ).getId();

            return cxt.setResult(draftId, DRAFT_ID);
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * lookup a draft by an identifier
     * <p>
     * GET /v1/{accountId}/drafts/{draftId}
     *
     * @param cxt a context
     * @return Draft
     */
    public QueryContext<ru.kontur.extern_api.sdk.model.Draft> lookup(QueryContext<ru.kontur.extern_api.sdk.model.Draft> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            return cxt.setResult(
                    new DraftDto().fromDto(
                            transport(cxt)
                                    .draftsGetDraft(
                                            cxt.getAccountProvider().accountId(),
                                            cxt.getDraftId()
                                    )
                    ),
                    DRAFT
            );
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * Delete a draft
     * <p>
     * DELETE /v1/{accountId}/drafts/{draftId}
     *
     * @param cxt a context
     * @return QueryContext&lt;Void&gt;
     */
    public QueryContext<Void> delete(QueryContext<Void> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            transport(cxt).draftsDeleteDraft(cxt.getAccountProvider().accountId(), cxt.getDraftId());

            return cxt.setResult(null, NOTHING);
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * lookup a draft meta by an identifier
     * <p>
     * GET /v1/{accountId}/drafts/{draftId}/meta
     *
     * @param cxt a context
     * @return DraftMeta
     */
    public QueryContext<ru.kontur.extern_api.sdk.model.DraftMeta> lookupDraftMeta(QueryContext<ru.kontur.extern_api.sdk.model.DraftMeta> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            return cxt.setResult(
                    new DraftMetaDto().fromDto(
                            transport(cxt).draftsGetMeta(
                                    cxt.getAccountProvider().accountId(),
                                    cxt.getDraftId()
                            )
                    ),
                    DRAFT_META
            );
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * update a draft meta
     * <p>
     * PUT /v1/{accountId}/drafts/{draftId}/meta
     *
     * @param cxt a context
     * @return DraftMeta
     */
    public QueryContext<ru.kontur.extern_api.sdk.model.DraftMeta> updateDraftMeta(QueryContext<ru.kontur.extern_api.sdk.model.DraftMeta> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            return cxt.setResult(
                    new DraftMetaDto().fromDto(
                            transport(cxt).draftsUpdateDraftMeta(
                                    cxt.getAccountProvider().accountId(),
                                    cxt.getDraftId(),
                                    new DraftMetaDto().toDto(cxt.getDraftMeta())
                            )
                    ),
                    DRAFT_META
            );
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * Operate CHECK
     * <p>
     * POST /v1/{accountId}/drafts/{draftId}/check
     *
     * @param cxt a context
     * @return Map&lt;String,Object&gt;
     */
    public QueryContext<Map<String, Object>> check(QueryContext<Map<String, Object>> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            return cxt.setResult(
                    (Map<String, Object>) transport(cxt).draftsCheck(
                            cxt.getAccountProvider().accountId(),
                            cxt.getDraftId()
                    ),
                    MAP
            );
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * Operate PREPARE
     * <p>
     * POST /v1/{accountId}/drafts/{draftId}/prepare
     *
     * @param cxt a context
     * @return PrepareResult;
     */
    public QueryContext<ru.kontur.extern_api.sdk.model.PrepareResult> prepare(QueryContext<ru.kontur.extern_api.sdk.model.PrepareResult> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            return cxt.setResult(
                    new PrepareResultDto().fromDto(
                            transport(cxt).draftsPrepare(
                                    cxt.getAccountProvider().accountId(),
                                    cxt.getDraftId()
                            )
                    ),
                    PREPARE_RESULT
            );

        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * Send the draft
     * <p>
     * POST /v1/{accountId}/drafts/drafts/{draftId}/send
     *
     * @param cxt a context
     * @return List&lt;Docflow&gt;
     */
    public QueryContext<List<Docflow>> send(QueryContext<List<Docflow>> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            DocflowDto docflowDto = new DocflowDto();

            return cxt.setResult(
                    transport(cxt).draftsSend(
                            cxt.getAccountProvider().accountId(),
                            cxt.getDraftId(),
                            cxt.getDeffered(),
                            cxt.getForce()
                    )
                            .stream()
                            .map(docflowDto::fromDto)
                            .collect(Collectors.toList())
                    , DOCFLOWS
            );
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * DELETE /v1/{accountId}/drafts/{draftId}/documents/{documentId}
     * <p>
     * Delete a document from the draft
     *
     * @param cxt a context (draftId, documentId)
     * @return QueryContext&lt;Void&gt;
     */
    public QueryContext<Void> deleteDocument(QueryContext<Void> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            transport(cxt).draftDocumentsDeleteDocument(cxt.getAccountProvider().accountId(), cxt.getDraftId(), cxt.getDocumentId());

            return cxt.setResult(null, NOTHING);
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * GET /v1/{accountId}/drafts/{draftId}/documents/{documentId}
     * <p>
     * lookup a document from the draft
     *
     * @param cxt a context (draftId,documentId)
     * @return DraftDocument
     */
    public QueryContext<DraftDocument> lookupDocument(QueryContext<DraftDocument> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            return cxt.setResult(new DraftDocumentDto().fromDto(
                    transport(cxt).draftDocumentsGetDocumentAsync(
                            cxt.getAccountProvider().accountId(),
                            cxt.getDraftId(),
                            cxt.getDocumentId()
                    )
                    ),
                    DRAFT_DOCUMENT
            ).setDocumentId(cxt.getDraftDocument().getId());
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * PUT /v1/{billingAccountId}/drafts/{draftId}/documents/{documentId}
     * <p>
     * Update a draft document Update the document
     *
     * @param cxt a context (required: draftId,documentId,DocumentContents)
     * @return DraftDocument
     */
    public QueryContext<DraftDocument> updateDocument(QueryContext<DraftDocument> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            DocumentContentsDto documentContentsDto = new DocumentContentsDto();

            return cxt.setResult(new DraftDocumentDto()
                            .fromDto(transport(cxt)
                                    .draftDocumentsPutDocument(
                                            cxt.getAccountProvider().accountId(),
                                            cxt.getDraftId(),
                                            cxt.getDocumentId(),
                                            documentContentsDto.toDto(cxt.getDocumentContents())
                                    )
                            ),
                    DRAFT_DOCUMENT
            ).setDocumentId(cxt.getDraftDocument().getId());
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * GET /v1/{accountId}/drafts/{draftId}/documents/{documentId}/print
     * <p>
     * print a document from the draft
     *
     * @param cxt a context (draftId,documentId)
     * @return DraftDocument
     */
    public QueryContext<String> printDocument(QueryContext<String> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            return cxt.setResult(
                    transport(cxt)
                            .draftDocumentsGetDocumentPrintAsyncWithHttpInfo(
                                    cxt.getAccountProvider().accountId(),
                                    cxt.getDraftId(),
                                    cxt.getDocumentId()
                            ).getData(),
                    CONTENT_STRING
            );
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * POST /v1/{accountId}/drafts/{draftId}/documents
     * <p>
     * Add a new document to the draft
     *
     * @param cxt a context (draftId,documentContent,fileName)
     * @return DraftDocument
     */
    public QueryContext<DraftDocument> addDecryptedDocument(QueryContext<DraftDocument> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            DocumentContentsDto documentContentsDto = new DocumentContentsDto();

            return cxt
                    .setResult(
                            new DraftDocumentDto()
                                    .fromDto(
                                            transport(cxt)
                                                    .draftDocumentsAddDocument(
                                                            cxt.getAccountProvider().accountId(),
                                                            cxt.getDraftId(),
                                                            documentContentsDto.toDto(cxt.getDocumentContents())
                                                    )
                                    ),
                            DRAFT_DOCUMENT
                    )
                    .setDocumentId(cxt.getDraftDocument().getId());
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * GET /v1/{accountId}/drafts/{draftId}/documents/{documentId}/content/decrypted
     * <p>
     * Get a decrypted document content
     *
     * @param cxt a context (required: draftId,documentId)
     * @return String
     */
    public QueryContext<String> getDecryptedDocumentContent(QueryContext<String> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            return cxt.setResult(transport(cxt)
                            .draftDocumentsGetDocumentContent(
                                    cxt.getAccountProvider().accountId(),
                                    cxt.getDraftId(),
                                    cxt.getDocumentId()
                            ),
                    CONTENT_STRING
            );
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * PUT /v1/{accountId}/drafts/{draftId}/documents/{documentId}/content/decrypted
     * <p>
     * Get a decrypted document content
     *
     * @param cxt a context (required: draftId,documentId)
     * @return QueryContext&lt;Void&gt;
     */
    public QueryContext<Void> updateDecryptedDocumentContent(QueryContext<Void> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            transport(cxt)
                    .draftDocumentsPutDocumentContent(
                            cxt.getAccountProvider().accountId(),
                            cxt.getDraftId(),
                            cxt.getDocumentId(),
                            cxt.getContent()
                    );

            return cxt.setResult(null, NOTHING);
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * GET /v1/{accountId}/drafts/{draftId}/documents/{documentId}/content/encrypted
     * <p>
     * Get a ecrypted document content
     *
     * @param cxt a context (required: draftId,documentId)
     * @return String
     */
    public QueryContext<String> getEncryptedDocumentContent(QueryContext<String> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            return cxt.setResult(transport(cxt)
                            .draftDocumentsGetEncryptedDocumentContent(
                                    cxt.getAccountProvider().accountId(),
                                    cxt.getDraftId(),
                                    cxt.getDocumentId()
                            ),
                    CONTENT_STRING
            );
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * GET /v1/{accountId}/drafts/{draftId}/documents/{documentId}/signature
     * <p>
     * Get a document signature
     *
     * @param cxt a context (required: draftId,documentId)
     * @return String
     */
    public QueryContext<String> getSignatureContent(QueryContext<String> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            return cxt.setResult(
                    transport(cxt)
                            .draftDocumentsGetSignatureContent(
                                    cxt.getAccountProvider().accountId(),
                                    cxt.getDraftId(),
                                    cxt.getDocumentId()
                            ),
                    CONTENT_STRING
            );
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * PUT /v1/{accountId}/drafts/{draftId}/documents/{documentId}/signature
     * <p>
     * Update a document signature
     *
     * @param cxt a context (required: draftId,documentId)
     * @return QueryContext&lt;Void&gt;
     */
    public QueryContext<Void> updateSignature(QueryContext<Void> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            transport(cxt)
                    .draftDocumentsPutDocumentSignature(
                            cxt.getAccountProvider().accountId(),
                            cxt.getDraftId(),
                            cxt.getDocumentId(),
                            cxt.getContent()
                    );

            return cxt.setResult(null, NOTHING);
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * POST /v1/{accountId}/drafts/{draftId}/documents/{documentId}/content/format/USN/1
     * <p>
     * Create an USN declaration, version 1
     *
     * @param cxt a context (required: draftId,documentId)
     * @return QueryContext&lt;Void&gt;
     */
    public QueryContext<Void> createUSN1(QueryContext<Void> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            transport(cxt)
                    .draftDocumentsBuildContentFromFormatWithHttpInfo(
                            cxt.getAccountProvider().accountId(),
                            cxt.getDraftId(),
                            cxt.getDocumentId(),
                            new UsnServiceContractInfoDto().toDto(cxt.getUsnServiceContractInfo())
                    );

            return cxt.setResult(null, NOTHING);
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    /**
     * POST /v1/{accountId}/drafts/{draftId}/documents/{documentId}/content/format/USN/2
     * <p>
     * Create an USN declaration, version 2
     *
     * @param cxt a context (required: draftId,documentId)
     * @return QueryContext&lt;Void&gt;
     */
    public QueryContext<Void> createUSN2(QueryContext<Void> cxt) {
        try {
            if (cxt.isFail()) return cxt;

            transport(cxt)
                    .draftDocumentsBuildContentFromFormat_0WithHttpInfo(
                            cxt.getAccountProvider().accountId(),
                            cxt.getDraftId(),
                            cxt.getDocumentId(),
                            new UsnServiceContractInfoV2Dto().toDto(cxt.getUsnServiceContractInfoV2())
                    );

            return cxt.setResult(null, NOTHING);
        } catch (ApiException x) {
            return cxt.setServiceError(new ApiExceptionDto().fromDto(x));
        }
    }

    private DraftsApi transport(QueryContext<?> cxt) {
        super.prepareTransport(cxt);
        return api;
    }
}