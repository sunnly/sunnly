package wang.sunnly.modules.logger.wrapper;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * ParamsRequestWrapper
 *
 * @author Sunnly
 * @since 2020/10/26 0026 21:15
 */
public class ParamsRequestWrapper extends HttpServletRequestWrapper {

    @Getter
    @Setter
    private String body;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IOException if the request is null
     */
    public ParamsRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = IOUtils.toString(request.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}