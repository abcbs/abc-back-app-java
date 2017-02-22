package com.tencent.business;

import com.tencent.protocol.pay_protocol.ScanPayResData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryResData;
import com.tencent.protocol.reverse_protocol.ReverseResData;

public class ScanPayBusinessImpl implements ScanPayBusiness.ResultListener {

	public void onFailByReturnCodeError(ScanPayResData scanPayResData) {
		// TODO Auto-generated method stub
		
	}

	public void onFailByReturnCodeFail(ScanPayResData scanPayResData) {
		// TODO Auto-generated method stub
		
	}

	public void onFailBySignInvalid(ScanPayResData scanPayResData) {
		// TODO Auto-generated method stub
		
	}

	public void onFailByQuerySignInvalid(ScanPayQueryResData scanPayQueryResData) {
		// TODO Auto-generated method stub
		
	}

	public void onFailByReverseSignInvalid(ReverseResData reverseResData) {
		// TODO Auto-generated method stub
		
	}

	public void onFailByAuthCodeExpire(ScanPayResData scanPayResData) {
		// TODO Auto-generated method stub
		
	}

	public void onFailByAuthCodeInvalid(ScanPayResData scanPayResData) {
		// TODO Auto-generated method stub
		
	}

	public void onFailByMoneyNotEnough(ScanPayResData scanPayResData) {
		// TODO Auto-generated method stub
		
	}

	public void onFail(ScanPayResData scanPayResData) {
		// TODO Auto-generated method stub
		
	}

	public void onSuccess(ScanPayResData scanPayResData, String transactionID) {
		// TODO Auto-generated method stub
		
	}

}
