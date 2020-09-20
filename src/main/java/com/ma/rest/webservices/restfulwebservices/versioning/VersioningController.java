package com.ma.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

	// handling version through different controllers having different URLs

	@GetMapping("/person/v1")
	public PersonV1 getV1() {
		return new PersonV1("Manoj Kumar");
	}

	@GetMapping("/person/v2")
	public PersonV2 getV2() {
		return new PersonV2(new Name("Manoj", "Kumar"));
	}

	// handling version through different parameters

	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 getParamV1() {
		return new PersonV1("Manoj Kumar");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 getParamV2() {
		return new PersonV2(new Name("Manoj", "Kumar"));
	}

	// handling version through different headers

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getHeaderV1() {
		return new PersonV1("Manoj Kumar");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getHeaderV2() {
		return new PersonV2(new Name("Manoj", "Kumar"));
	}

	// handling version through different Produces or accept or accept-header version or MIME type version or content-negotiation

	@GetMapping(value = "/person/produces", produces = "application/v1+json")
	public PersonV1 getProduceV1() {
		return new PersonV1("Manoj Kumar");
	}

	@GetMapping(value = "/person/produces", produces = "application/v2+json")
	public PersonV2 getProduceV2() {
		return new PersonV2(new Name("Manoj", "Kumar"));
	}
}
