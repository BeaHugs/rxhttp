package org.beahugs.libs.network.interfaces;

import java.util.Map;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public interface BaseHeadersListener {
    Map<String, String> buildHeaders();
}
