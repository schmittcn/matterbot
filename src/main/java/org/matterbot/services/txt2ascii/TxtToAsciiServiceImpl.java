package org.matterbot.services.txt2ascii;

import lombok.extern.slf4j.Slf4j;
import org.matterbot.services.BaseURLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class TxtToAsciiServiceImpl extends BaseURLQueryService {
    private final TxtToAsciiClient txtToAsciiClient;

    @Autowired
    public TxtToAsciiServiceImpl(final TxtToAsciiClient txtToAsciiClient) {
        this.txtToAsciiClient = txtToAsciiClient;
    }

    @Override
    public String getUrl(final Strategy strategy) {
        return getUrl(strategy, "");
    }

    @Override
    public String getUrl(final Strategy strategy, final String term) {
        try {
            Call<String> call;
            if (strategy == Strategy.SEARCH) {
                call = txtToAsciiClient.getAsciiArt(term);
            } else {
                return "WHAT DO YOU WANT, DUDE?";
            }
            return call.execute().body();
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return "";
    }

    @Override
    public List<String> getUrlList(final String term) {
        return null;
    }
}
