package com.gss.gmo.cao.integration.xmpp.component.service.relay;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class PureMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String type;
	private String subject;
	private String body;
	private String ID;
	private String to;
	private String from;

}
