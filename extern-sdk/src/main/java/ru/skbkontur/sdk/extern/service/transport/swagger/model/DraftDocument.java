/*
 * Kontur.Extern.Api.Public
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package ru.skbkontur.sdk.extern.service.transport.swagger.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.UUID;
import ru.skbkontur.sdk.extern.service.transport.swagger.model.DocumentDescription;
import ru.skbkontur.sdk.extern.service.transport.swagger.model.Link;

/**
 * DraftDocument
 */

public class DraftDocument {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("decrypted-content-link")
  private Link decryptedContentLink = null;

  @SerializedName("encrypted-content-link")
  private Link encryptedContentLink = null;

  @SerializedName("signature-content-link")
  private Link signatureContentLink = null;

  @SerializedName("description")
  private DocumentDescription description = null;

  public DraftDocument id(UUID id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "00000000-0000-0000-0000-000000000000", value = "")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public DraftDocument decryptedContentLink(Link decryptedContentLink) {
    this.decryptedContentLink = decryptedContentLink;
    return this;
  }

   /**
   * Get decryptedContentLink
   * @return decryptedContentLink
  **/
  @ApiModelProperty(value = "")
  public Link getDecryptedContentLink() {
    return decryptedContentLink;
  }

  public void setDecryptedContentLink(Link decryptedContentLink) {
    this.decryptedContentLink = decryptedContentLink;
  }

  public DraftDocument encryptedContentLink(Link encryptedContentLink) {
    this.encryptedContentLink = encryptedContentLink;
    return this;
  }

   /**
   * Get encryptedContentLink
   * @return encryptedContentLink
  **/
  @ApiModelProperty(value = "")
  public Link getEncryptedContentLink() {
    return encryptedContentLink;
  }

  public void setEncryptedContentLink(Link encryptedContentLink) {
    this.encryptedContentLink = encryptedContentLink;
  }

  public DraftDocument signatureContentLink(Link signatureContentLink) {
    this.signatureContentLink = signatureContentLink;
    return this;
  }

   /**
   * Get signatureContentLink
   * @return signatureContentLink
  **/
  @ApiModelProperty(value = "")
  public Link getSignatureContentLink() {
    return signatureContentLink;
  }

  public void setSignatureContentLink(Link signatureContentLink) {
    this.signatureContentLink = signatureContentLink;
  }

  public DraftDocument description(DocumentDescription description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  public DocumentDescription getDescription() {
    return description;
  }

  public void setDescription(DocumentDescription description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DraftDocument draftDocument = (DraftDocument) o;
    return Objects.equals(this.id, draftDocument.id) &&
        Objects.equals(this.decryptedContentLink, draftDocument.decryptedContentLink) &&
        Objects.equals(this.encryptedContentLink, draftDocument.encryptedContentLink) &&
        Objects.equals(this.signatureContentLink, draftDocument.signatureContentLink) &&
        Objects.equals(this.description, draftDocument.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, decryptedContentLink, encryptedContentLink, signatureContentLink, description);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DraftDocument {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    decryptedContentLink: ").append(toIndentedString(decryptedContentLink)).append("\n");
    sb.append("    encryptedContentLink: ").append(toIndentedString(encryptedContentLink)).append("\n");
    sb.append("    signatureContentLink: ").append(toIndentedString(signatureContentLink)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

