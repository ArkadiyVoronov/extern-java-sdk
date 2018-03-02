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
import ru.skbkontur.sdk.extern.service.transport.swagger.model.DocumentDescription;

/**
 * DocumentContents
 */

public class DocumentContents {
  @SerializedName("base64-content")
  private String base64Content = null;

  @SerializedName("signature")
  private String signature = null;

  @SerializedName("description")
  private DocumentDescription description = null;

  public DocumentContents base64Content(String base64Content) {
    this.base64Content = base64Content;
    return this;
  }

   /**
   * Get base64Content
   * @return base64Content
  **/
  @ApiModelProperty(value = "")
  public String getBase64Content() {
    return base64Content;
  }

  public void setBase64Content(String base64Content) {
    this.base64Content = base64Content;
  }

  public DocumentContents signature(String signature) {
    this.signature = signature;
    return this;
  }

   /**
   * Get signature
   * @return signature
  **/
  @ApiModelProperty(value = "")
  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public DocumentContents description(DocumentDescription description) {
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
    DocumentContents documentContents = (DocumentContents) o;
    return Objects.equals(this.base64Content, documentContents.base64Content) &&
        Objects.equals(this.signature, documentContents.signature) &&
        Objects.equals(this.description, documentContents.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(base64Content, signature, description);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentContents {\n");
    
    sb.append("    base64Content: ").append(toIndentedString(base64Content)).append("\n");
    sb.append("    signature: ").append(toIndentedString(signature)).append("\n");
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

