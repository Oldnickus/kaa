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

package org.kaaproject.kaa.client;

import org.kaaproject.kaa.client.exceptions.KaaInvalidConfigurationException;
import org.kaaproject.kaa.client.exceptions.KaaRuntimeException;
import org.kaaproject.kaa.client.exceptions.KaaUnsupportedPlatformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Creates new Kaa client based on {@link KaaClientPlatformContext platform
 * context} and optional {@link KaaClientStateListener state listener}.
 *
 * @author Andrew Shvayka
 */
public class Kaa {
  private static final Logger LOG = LoggerFactory.getLogger(Kaa.class);

  private Kaa() {
  }

  /**
   * Creating new Kaa client
   *
   * @param context             Kaa platform specific context for client init
   * @param listener            notifier for changing client state
   * @param isAutogeneratedKeys check whether to generate public/private key pair automatically.
   *                            'false' - use existing pre-generated public/private key pair
   *                            (default behaviour). 'true'  - auto-generate new public/private key
   *                            pair if absent.
   * @return new Kaa client
   * @throws KaaRuntimeException if there are now key pair
   */
  public static KaaClient newClient(KaaClientPlatformContext context,
                                    KaaClientStateListener listener,
                                    boolean isAutogeneratedKeys) throws KaaRuntimeException {
    try {
      return new BaseKaaClient(context, listener, isAutogeneratedKeys);
    } catch (GeneralSecurityException ex) {
      LOG.error("Failed to create Kaa client", ex);
      throw new KaaUnsupportedPlatformException(ex);
    } catch (IOException ex) {
      LOG.error("Failed to create Kaa client", ex);
      throw new KaaInvalidConfigurationException(ex);
    }
  }
}
