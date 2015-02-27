/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.takes.rq;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import lombok.EqualsAndHashCode;
import org.takes.Request;

/**
 * Plain request.
 *
 * @author Yegor Bugayenko (yegor@teamed.io)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode(of = { "hde", "content" })
public final class RqPlain implements Request {

    /**
     * Head.
     */
    private final transient List<String> hde;

    /**
     * Content.
     */
    private final transient String content;

    /**
     * Ctor.
     * @param method HTTP method
     * @param query HTTP query
     * @param body HTTP body
     */
    public RqPlain(final String method, final String query, final String body) {
        this(
            Collections.singletonList(
                String.format("%s %s", method, query)
            ),
            body
        );
    }

    /**
     * Ctor.
     * @param head Head
     * @param body Body
     */
    public RqPlain(final List<String> head, final String body) {
        this.hde = Collections.unmodifiableList(head);
        this.content = body;
    }

    @Override
    public List<String> head() {
        return Collections.unmodifiableList(this.hde);
    }

    @Override
    public InputStream body() {
        try {
            return new ByteArrayInputStream(this.content.getBytes("UTF-8"));
        } catch (final UnsupportedEncodingException ex) {
            throw new IllegalStateException(ex);
        }
    }

}