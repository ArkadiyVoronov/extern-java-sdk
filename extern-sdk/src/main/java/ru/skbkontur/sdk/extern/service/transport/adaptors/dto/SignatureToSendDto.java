/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.skbkontur.sdk.extern.service.transport.adaptors.dto;

/**
 *
 * @author AlexS
 */
public class SignatureToSendDto {

	public SignatureToSendDto() {
	}
	
	public ru.skbkontur.sdk.extern.model.SignatureToSend fromDto(ru.skbkontur.sdk.extern.service.transport.swagger.model.SignatureToSend dto) {
		ru.skbkontur.sdk.extern.model.SignatureToSend signatureToSend = new ru.skbkontur.sdk.extern.model.SignatureToSend();
		
		signatureToSend.setContentData(dto.getContentData());
		signatureToSend.setId(dto.getId());
		
		return signatureToSend;
	}

	public ru.skbkontur.sdk.extern.service.transport.swagger.model.SignatureToSend toDto(ru.skbkontur.sdk.extern.model.SignatureToSend signatureToSend) {
		ru.skbkontur.sdk.extern.service.transport.swagger.model.SignatureToSend dto = new ru.skbkontur.sdk.extern.service.transport.swagger.model.SignatureToSend();
		
		dto.setContentData(signatureToSend.getContentData());
		dto.setId(signatureToSend.getId());
		
		return dto;
	}
}