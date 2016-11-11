package com.cn.hash.myspace.es;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/11.
 */
public class ESClient {
    private final Client client;
    private final static Logger log = LoggerFactory.getLogger(ESClient.class);

    public ESClient(String clusterName, String hosts) throws UnknownHostException {
        Settings settings = Settings.settingsBuilder()
                .put("client.transport.sniff", true)
                .put("cluster.name", clusterName)
                .build();
        String host[] = hosts.split(",");
        TransportClient build = TransportClient.builder().settings(settings).build();
        for (int i = 0; i < host.length; i++) {
            build.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host[i].substring(0, host[i].indexOf(":"))), Integer.parseInt(host[i].substring(host[i].indexOf(":") + 1))));
        }
        client = build;
    }

    public void addOrUpdateDocument(String index, String type, String id, Object document) {
        byte[] json = JSON.toJSONBytes(document);
        IndexResponse indexResponse = client.prepareIndex(index, type, id).setSource(json).get();
        log.info(indexResponse.toString());
    }

    public void deleteDocument(String index, String type, String id) {
        DeleteResponse deleteResponse = client.prepareDelete(index, type, id).get();
    }

    public SearchHit[] searchDocument(String[] indexes, String[] types, QueryBuilder queryBuilder) {
        SearchResponse searchResponse = client.prepareSearch(indexes).setTypes(types).addHighlightedField("introduction").setQuery(queryBuilder).setHighlighterPreTags("<oops>").setHighlighterPostTags("</oops>").setSize(100).execute().actionGet();
        return searchResponse.getHits().getHits();
    }
}
