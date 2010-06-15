package com.codefreak.iphonestats.modules.weatherbug;

import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

public class AWSNameSpaceContext implements NamespaceContext {

	@Override
	public String getNamespaceURI(String prefix) {
		String uri;
		if (prefix.equals("aws")) uri = "http://www.aws.com/aws";
		else uri = null;
		return uri;	
	}

	@Override
	public String getPrefix(String namespaceURI) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator getPrefixes(String namespaceURI) {
		// TODO Auto-generated method stub
		return null;
	}

}
