package com.example.todo.app;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;

public class StreamFileModelAttributeMethodProcessor extends ModelAttributeMethodProcessor {
	public StreamFileModelAttributeMethodProcessor() {
		super(false);
	}

	// ★ポイント3
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return StreamFile.class.equals(parameter.getParameterType());
	}

	// ★ポイント4
	@Override
	protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {

		// ★ポイント5
		HttpServletRequest httpRequest = request.getNativeRequest(HttpServletRequest.class);
		ServletRequestParameterPropertyValues pvs = new ServletRequestParameterPropertyValues(httpRequest);

		// ★ポイント6
		pvs.add("contentType", httpRequest.getContentType());
		pvs.add("contentLength", httpRequest.getContentLengthLong());
		pvs.add("fileName", httpRequest.getHeader("X-FILE-NAME"));

		// ★ポイント7
		try {
			pvs.add("inputStream", httpRequest.getInputStream());
		} catch (IOException e) {
			pvs.add("inputStream", null);
		}

		// ★ポイント8
		binder.bind(pvs);
	}

}
