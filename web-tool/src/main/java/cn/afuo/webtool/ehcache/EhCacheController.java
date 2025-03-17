package cn.afuo.webtool.ehcache;


import cn.afuo.webtool.constant.CacheConstants;
import cn.afuo.webtool.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/ehcache")
public class EhCacheController {


    @PostMapping("/add")
    @CachePut(value = CacheConstants.CACHE_NAME, key = "#ehCacheVO.userId")
    public EhCacheVO add(@RequestBody EhCacheVO ehCacheVO) {
        log.info("新增缓存 {}", JsonUtil.toJson(ehCacheVO));
        return ehCacheVO;
    }


    @GetMapping("/get/{userId}")
    @Cacheable(value = CacheConstants.CACHE_NAME, key = "#userId")
    public EhCacheVO getById(@PathVariable long userId) {
        log.info("获取缓存 {}", userId);
        return null;
    }


    @PutMapping("/edit")
    @CachePut(value = CacheConstants.CACHE_NAME, key = "#ehCacheVO.userId")
    public EhCacheVO edit(@RequestBody EhCacheVO ehCacheVO) {
        log.info("编辑缓存 {}", ehCacheVO);
        return ehCacheVO;
    }


    @DeleteMapping("/del/{userId}")
    @CacheEvict(value = CacheConstants.CACHE_NAME, key = "#userId")
    public long delById(@PathVariable long userId) {
        log.info("删除缓存 {}", userId);
        return userId;
    }


    // spring-cache 中 cache转map
    // public static Map<String, Object> cacheToMap(Cache cache) {
    //     Object obj = cache.getNativeCache();
    //     Map<String, Object> map = new HashMap<>();
    //     Field[] fields = obj.getClass().getDeclaredFields();
    //     try {
    //         for (Field field : fields) {
    //             field.setAccessible(true);
    //             map.put(field.getName(), field.get(obj));
    //         }
    //     } catch (Exception e) {
    //         log.error("ObjToMap exp", e);
    //         return null;
    //     }
    //     // 获取Cache.map中的cache
    //     return (Map<String, Object>) map.get("cache");
    // }

}
