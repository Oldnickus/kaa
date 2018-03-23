/*
 * Copyright 2014-2016 CyberVision, Inc.
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

package org.kaaproject.kaa.server.admin.client.mvp.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;

import org.kaaproject.kaa.common.dto.ProfileFilterDto;
import org.kaaproject.kaa.server.admin.client.mvp.view.widget.VersionListBox;

public interface ProfileFilterView extends BaseRecordView<ProfileFilterDto, String> {

  public VersionListBox getEndpointProfileSchema();

  public HasValue<String> getEndpointProfileSchemaVersion();

  public VersionListBox getServerProfileSchema();

  public HasValue<String> getServerProfileSchemaVersion();

  public HasClickHandlers getTestFilterButton();

}
