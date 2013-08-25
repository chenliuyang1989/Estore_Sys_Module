package com.gmcc.service;

import com.gmcc.model.Params;

public interface ParamsManager {
	public abstract Params getParamsByCode(String code) throws Exception;	
}
