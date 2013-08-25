/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gmcc.exception;

import org.springframework.security.AccountStatusException;

/**
 * Thrown if an authentication request is rejected because the account is locked. Makes no assertion as to whether
 * or not the credentials were valid.
 *
 * @author Ben Alex
 * @version $Id: LockedException.java 2653 2008-02-18 20:18:40Z luke_t $
 */
public class SaUserException extends AccountStatusException {
    //~ Constructors ===================================================================================================

    /**
     * Constructs a <code>LockedException</code> with the specified message.
     *
     * @param msg the detail message.
     */
    public SaUserException(String msg) {
        super(msg);
    }

    /**
     * Constructs a <code>LockedException</code> with the specified message and
     * root cause.
     *
     * @param msg the detail message.
     * @param t root cause
     */
    public SaUserException(String msg, Throwable t) {
        super(msg, t);
    }

    public SaUserException(String msg, Object extraInformation) {
        super(msg, extraInformation);
    }
}
