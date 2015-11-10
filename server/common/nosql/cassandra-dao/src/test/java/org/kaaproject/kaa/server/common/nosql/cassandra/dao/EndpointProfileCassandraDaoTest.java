/*
 * Copyright 2014 CyberVision, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kaaproject.kaa.server.common.nosql.cassandra.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kaaproject.kaa.common.dto.EndpointGroupDto;
import org.kaaproject.kaa.common.dto.EndpointGroupStateDto;
import org.kaaproject.kaa.common.dto.EndpointProfileBodyDto;
import org.kaaproject.kaa.common.dto.EndpointProfileDto;
import org.kaaproject.kaa.common.dto.EndpointProfilesBodyDto;
import org.kaaproject.kaa.common.dto.EndpointProfilesPageDto;
import org.kaaproject.kaa.common.dto.EndpointUserDto;
import org.kaaproject.kaa.common.dto.PageLinkDto;
import org.kaaproject.kaa.server.common.dao.model.EndpointProfile;
import org.kaaproject.kaa.server.common.nosql.cassandra.dao.model.CassandraEndpointProfile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/cassandra-client-test-context.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class EndpointProfileCassandraDaoTest extends AbstractCassandraTest {

    private static final String TEST_APPID = "1";
    private static final String TEST_LIMIT = "3";
    private static final String TEST_OFFSET = "0";
    private static final int GENERATED_PROFILES_COUNT = 5;

    @Test
    public void testFindByEndpointGroupId() throws Exception {
        List<EndpointProfileDto> endpointProfileList = new ArrayList<>();
        for (int i = 0; i < GENERATED_PROFILES_COUNT; i++) {
            endpointProfileList.add(generateEndpointProfileWithEndpointGroupId(TEST_APPID, false));
        }
        String id = endpointProfileList.get(0).getCfGroupStates().get(0).getEndpointGroupId();
        int lim = Integer.valueOf(TEST_LIMIT);
        PageLinkDto pageLink = new PageLinkDto(id, TEST_LIMIT, TEST_OFFSET);
        EndpointProfilesPageDto found = endpointProfileDao.findByEndpointGroupId(pageLink);
        Assert.assertFalse(found.getEndpointProfiles().isEmpty());
        Assert.assertEquals(lim, found.getEndpointProfiles().size());
        pageLink.setApplicationId(TEST_APPID);
        EndpointProfilesPageDto foundbyAppId = endpointProfileDao.findByEndpointGroupId(pageLink);
        Assert.assertFalse(foundbyAppId.getEndpointProfiles().isEmpty());
        Assert.assertEquals(lim, foundbyAppId.getEndpointProfiles().size());
    }

    @Test
    public void testFindByEndpointGroupIdWithNfGroupState() throws Exception {
        List<EndpointProfileDto> endpointProfileList = new ArrayList<>();
        for (int i = 0; i < GENERATED_PROFILES_COUNT; i++) {
            endpointProfileList.add(generateEndpointProfileWithEndpointGroupId(TEST_APPID, true));
        }
        String id = endpointProfileList.get(0).getNfGroupStates().get(0).getEndpointGroupId();
        int lim = Integer.valueOf(TEST_LIMIT);
        PageLinkDto pageLink = new PageLinkDto(id, TEST_LIMIT, TEST_OFFSET);
        EndpointProfilesPageDto found = endpointProfileDao.findByEndpointGroupId(pageLink);
        Assert.assertFalse(found.getEndpointProfiles().isEmpty());
        Assert.assertEquals(lim, found.getEndpointProfiles().size());
        pageLink.setApplicationId(TEST_APPID);
        EndpointProfilesPageDto foundbyAppId = endpointProfileDao.findByEndpointGroupId(pageLink);
        Assert.assertFalse(foundbyAppId.getEndpointProfiles().isEmpty());
        Assert.assertEquals(lim, foundbyAppId.getEndpointProfiles().size());
    }

    @Test
    public void testFindBodyByEndpointGroupId() throws Exception {
        List<EndpointProfileDto> endpointProfileList = new ArrayList<>();
        for (int i = 0; i < GENERATED_PROFILES_COUNT; i++) {
            endpointProfileList.add(generateEndpointProfileWithEndpointGroupId(TEST_APPID, false));
        }
        String id = endpointProfileList.get(0).getCfGroupStates().get(0).getEndpointGroupId();
        int lim = Integer.valueOf(TEST_LIMIT);
        PageLinkDto pageLink = new PageLinkDto(id, TEST_LIMIT, TEST_OFFSET);
        EndpointProfilesBodyDto found = endpointProfileDao.findBodyByEndpointGroupId(pageLink);
        Assert.assertFalse(found.getEndpointProfilesBody().isEmpty());
        Assert.assertEquals(lim, found.getEndpointProfilesBody().size());
        pageLink.setApplicationId(TEST_APPID);
        EndpointProfilesBodyDto foundbyAppId = endpointProfileDao.findBodyByEndpointGroupId(pageLink);
        Assert.assertFalse(foundbyAppId.getEndpointProfilesBody().isEmpty());
        Assert.assertEquals(lim, foundbyAppId.getEndpointProfilesBody().size());
    }

    @Test
    public void testFindBodyByEndpointGroupIdWithNfGroupState() throws Exception {
        List<EndpointProfileDto> endpointProfileList = new ArrayList<>();
        for (int i = 0; i < GENERATED_PROFILES_COUNT; i++) {
            endpointProfileList.add(generateEndpointProfileWithEndpointGroupId(TEST_APPID, true));
        }
        String id = endpointProfileList.get(0).getNfGroupStates().get(0).getEndpointGroupId();
        int lim = Integer.valueOf(TEST_LIMIT);
        PageLinkDto pageLink = new PageLinkDto(id, TEST_LIMIT, TEST_OFFSET);
        EndpointProfilesBodyDto found = endpointProfileDao.findBodyByEndpointGroupId(pageLink);
        Assert.assertFalse(found.getEndpointProfilesBody().isEmpty());
        Assert.assertEquals(lim, found.getEndpointProfilesBody().size());
        pageLink.setApplicationId(TEST_APPID);
        EndpointProfilesBodyDto foundbyAppId = endpointProfileDao.findBodyByEndpointGroupId(pageLink);
        Assert.assertFalse(foundbyAppId.getEndpointProfilesBody().isEmpty());
        Assert.assertEquals(lim, foundbyAppId.getEndpointProfilesBody().size());
    }

    @Test
    public void testFindBodyByKeyHash() throws Exception {
        EndpointProfileDto expected = generateEndpointProfileWithEndpointGroupId(null,false);
        EndpointProfileBodyDto found = endpointProfileDao.findBodyByKeyHash(expected.getEndpointKeyHash());
        Assert.assertFalse(found.getProfile().isEmpty());
        Assert.assertEquals(expected.getProfile(), found.getProfile());
    }

    @Test
    public void testUpdate() throws Exception {
        List<EndpointGroupStateDto> cfGroupStateSave = new ArrayList<EndpointGroupStateDto>();
        List<EndpointGroupStateDto> cfGroupStateUpdate = new ArrayList<EndpointGroupStateDto>();
        PageLinkDto pageLink;
        EndpointProfilesPageDto found;
        String endpointProfileId = "11";
        EndpointGroupDto endpointGroupDto = new EndpointGroupDto();
        endpointGroupDto.setWeight(1);
        cfGroupStateSave.add(new EndpointGroupStateDto("111", null, null));
        cfGroupStateSave.add(new EndpointGroupStateDto("222", null, null));
        cfGroupStateSave.add(new EndpointGroupStateDto("333", null, null));
        EndpointProfileDto endpointProfileSave = generateEndpointProfileForTestUpdate(null, cfGroupStateSave);
        endpointProfileDao.save(endpointProfileSave);
        cfGroupStateUpdate.add(new EndpointGroupStateDto("111", null, null));
        cfGroupStateUpdate.add(new EndpointGroupStateDto("444", null, null));
        EndpointProfileDto endpointProfileUpdate = generateEndpointProfileForTestUpdate(endpointProfileId, cfGroupStateUpdate);
        endpointProfileDao.save(endpointProfileUpdate);
        String limit = "10";
        String offset = "0";
        String[] endpointGroupId = {"111", "444", "222", "333"};
        for (int i = 0; i < 2; i++) {
            pageLink = new PageLinkDto(endpointGroupId[i], limit, offset);
            found = endpointProfileDao.findByEndpointGroupId(pageLink);
            Assert.assertFalse(found.getEndpointProfiles().isEmpty());
        }
        for (int i = 2; i < 4; i++) {
            pageLink = new PageLinkDto(endpointGroupId[i], limit, offset);
            found = endpointProfileDao.findByEndpointGroupId(pageLink);
            Assert.assertTrue(found.getEndpointProfiles().isEmpty());
        }
    }

    @Test
    public void testSave() throws Exception {
        EndpointProfileDto endpointProfile = generateEndpointProfile(null, null, null, null);
        EndpointProfile found = endpointProfileDao.findByKeyHash(endpointProfile.getEndpointKeyHash());
        Assert.assertEquals(endpointProfile, found.toDto());
    }

    @Test
    public void testFindByKeyHash() throws Exception {
        EndpointProfileDto expected = generateEndpointProfile(null, null, null, null);
        EndpointProfile found = endpointProfileDao.findByKeyHash(expected.getEndpointKeyHash());
        Assert.assertEquals(expected, found.toDto());
    }

    @Test
    public void testGetCountByKeyHash() throws Exception {
        EndpointProfileDto endpointProfile = generateEndpointProfile(null, null, null, null);
        long count = endpointProfileDao.getCountByKeyHash(endpointProfile.getEndpointKeyHash());
        Assert.assertEquals(1L, count);
    }

    @Test
    public void testRemoveByKeyHash() throws Exception {
        EndpointProfileDto expected = generateEndpointProfile(null, null, null, null);
        endpointProfileDao.removeByKeyHash(expected.getEndpointKeyHash());
        EndpointProfile found = endpointProfileDao.findByKeyHash(expected.getEndpointKeyHash());
        Assert.assertNull(found);
    }

    @Test
    public void testRemoveById() throws Exception {
        EndpointProfileDto expected = generateEndpointProfile(null, null, null, null);
        endpointProfileDao.removeById(ByteBuffer.wrap(expected.getEndpointKeyHash()));
        EndpointProfile found = endpointProfileDao.findByKeyHash(expected.getEndpointKeyHash());
        Assert.assertNull(found);
    }

    @Test
    public void testRemoveByIdNullKey() throws Exception {
        EndpointProfileDto expected = generateEndpointProfile(null, null, null, null);
        EndpointProfile found = endpointProfileDao.findByKeyHash(expected.getEndpointKeyHash());
        Assert.assertNotNull(found);
    }

    @Test
    public void testFindById() throws Exception {
        EndpointProfileDto expected = generateEndpointProfile(null, null, null, null);
        EndpointProfile found = endpointProfileDao.findById(ByteBuffer.wrap(expected.getEndpointKeyHash()));
        Assert.assertEquals(expected, found.toDto());
    }

    @Test
    public void testFindByIdNullKey() throws Exception {
        EndpointProfileDto expected = generateEndpointProfile(null, null, null, null);
        EndpointProfile found = endpointProfileDao.findById(null);
        Assert.assertNull(found);
    }

    @Test
    public void testFindByAccessToken() throws Exception {
        EndpointProfileDto expected = generateEndpointProfile(null, null, null, null);
        EndpointProfile found = endpointProfileDao.findByAccessToken(expected.getAccessToken());
        Assert.assertEquals(expected, found.toDto());
    }

    @Test
    public void testFindByEndpointUserId() throws Exception {
        EndpointProfileDto endpointProfileDto = generateEndpointProfile(null, null, null, null);
        EndpointUserDto endpointUserDto = generateEndpointUser(Arrays.asList(endpointProfileDto.getId()));
        List<CassandraEndpointProfile> found = endpointProfileDao.findByEndpointUserId(endpointUserDto.getId());
        Assert.assertFalse(found.isEmpty());
        Assert.assertEquals(endpointProfileDto, found.get(0).toDto());
    }

    @Test
    public void testCheckSdkToken() throws Exception {
        this.generateEndpointProfile(null, "alpha", null, null);
        Assert.assertTrue(endpointProfileDao.checkSdkToken("alpha"));
        Assert.assertFalse(endpointProfileDao.checkSdkToken("beta"));
    }
}