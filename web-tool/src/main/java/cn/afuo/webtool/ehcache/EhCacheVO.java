package cn.afuo.webtool.ehcache;

import lombok.Data;

import java.io.Serializable;

@Data
public class EhCacheVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

}
