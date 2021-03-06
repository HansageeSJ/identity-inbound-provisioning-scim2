/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.inbound.provisioning.scim2.common.test.unit;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.inbound.provisioning.scim2.common.exception.SCIMClientException;
import org.wso2.carbon.identity.inbound.provisioning.scim2.provider.mappers.SCIMClientExceptionMapper;
import org.wso2.carbon.identity.mgt.exception.IdentityStoreException;

import javax.ws.rs.core.Response;

/**
 * Unit tests for SCIM Client Mapper
 */
public class SCIMClientExceptionMapperTests {

    @Test
    public void testToResponse() {
        String errorMessage = "Error in getting a RealmService Instance.";
        IdentityStoreException identityStoreException = new IdentityStoreException(errorMessage);

        SCIMClientException scimClientException = new SCIMClientException(identityStoreException.getMessage(),
                identityStoreException, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

        SCIMClientExceptionMapper scimClientExceptionMapper = new SCIMClientExceptionMapper();
        Response response = scimClientExceptionMapper.toResponse(scimClientException);

        Assert.assertNotNull(response, "Failed to receive the Response object through SCIMClientExceptionMapper.");
        Assert.assertEquals(Integer.toString(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()),
                Integer.toString(response.getStatus()), "Failed in correctly mapping the exception.");
    }
}
