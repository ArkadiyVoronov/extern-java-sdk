/*
 * MIT License
 *
 * Copyright (c) 2018 SKB Kontur
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ru.skbkontur.sdk.extern.providers.auth;

import ru.skbkontur.sdk.extern.event.AuthenticationEvent;
import ru.skbkontur.sdk.extern.event.AuthenticationListener;
import ru.skbkontur.sdk.extern.providers.AuthenticationProvider;
import ru.skbkontur.sdk.extern.providers.ServiceError;
import ru.skbkontur.sdk.extern.service.transport.adaptors.QueryContext;

import java.util.ArrayList;
import java.util.List;


/**
 * @author alexs
 */
public abstract class AuthenticationProviderAbstract implements AuthenticationProvider {

    protected static final String DEFAULT_AUTH_PREFIX = "auth.sid ";

    private final List<AuthenticationListener> authListeners;

    protected AuthenticationProviderAbstract() {
        this.authListeners = new ArrayList<>();
    }

    @Override
    public void addAuthenticationListener(AuthenticationListener authenticationListener) {
        synchronized (authListeners) {
            authListeners.add(authenticationListener);
        }
    }

    @Override
    public void removeAuthenticationListener(AuthenticationListener authenticationListener) {
        synchronized (authListeners) {
            authListeners.add(authenticationListener);
        }
    }

    private List<AuthenticationListener> getAuthenticationListener() {
        List<AuthenticationListener> cloned = new ArrayList<>();
        synchronized (authListeners) {
            authListeners.forEach(a -> cloned.add(a));
        }
        return cloned;
    }

    @Override
    public void raiseUnauthenticated(ServiceError x) {
        QueryContext<String> authCxt = new QueryContext();
        authCxt.setServiceError(x);
        fireAuthenticationEvent(authCxt);
    }

    protected void fireAuthenticationEvent(QueryContext<String> authCxt) {
        List<AuthenticationListener> clonedAuthListeners = getAuthenticationListener();
        if (clonedAuthListeners != null) {
            clonedAuthListeners.stream().forEach(l -> l.authenticate(new AuthenticationEvent(AuthenticationProviderAbstract.this, authCxt)));
        }
    }
}