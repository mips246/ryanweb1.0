package cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Date;

public class CacheMgr {
	private static Map<String, String> cacheMap = new HashMap()<>();
	private static Map<String, CacheConfModel> cacheConfMap = new HashMap<String, CacheConfModel>();
    private static CacheMgr cm = null;
    private static CacheConfModel general = null;
    
    //构造方法  
    private CacheMgr(){
    }
    //单例执行
    public static CacheMgr getInstance(){
    	if(cm==null){
    		cm = new CacheMgr();
    		Thread t = new ClearCache();
    		t.setName("clear_t");
    		t.start();
    	}
    	
    	return cm;
    }
    
    public static CacheConfModel getGeneralModel(long time) {
		if (general == null) {
			general = new CacheConfModel();
			general.setDurableTime(60*2);
		}
		general.setBeginTime(time);
		
		return general;
	}
    
    /** 
     * 增加缓存 
     * @param key 
     * @param value 
     * @param ccm 缓存对象 
     * @return  
     */
    public  boolean addCache(String key,String value,CacheConfModel ccm){
    	// 标记缓存增加执行是否成功
    	boolean flag = false;
        try {
        	// 开始增加缓存
        	cacheMap.put(key, value);
            cacheConfMap.put(key, ccm);
            System.out.println("< 执行增加cookie > Size: "+cacheMap.size());  
            // 增加缓存结束
            flag=true;
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return flag;  
    }  
      
      
      /** 
       * 获取缓存实体 
       */  
    public String getValue(String key){
    	for (String k : cacheMap.keySet()) {
			if (k.equals(key)) {
				String o = cacheMap.get(k);
				return o;
			}
		}
		return null;
    }  
      
      
    /** 
     * 获取缓存数据的数量 
     * @return 
     */  
    public int getSize(){  
    	return cacheMap.size();  
    }
   
    /** 
     * 删除缓存 
     * @param key 
     * @return  
     */  
    public  boolean removeCache(Object key){  
    	boolean flag=false;  
        try {  
        	cacheMap.remove(key);  
            cacheConfMap.remove(key);  
            System.out.println("< 执行清除cookie > Size: "+cacheMap.size()); 
            flag=true;  
        } catch (Exception e) {  
        	e.printStackTrace();  
        }  
        return flag;  
    }
    
    public static void stopClearThread() {
		int n = Thread.activeCount();
		Thread[] threads = new Thread[n];
		Thread.enumerate(threads);
		for (Thread thread : threads) {
			if (thread.getName().equals("clear_t")) {
				thread.interrupt();
			}
		}
	}
    
    /** 
     * 清除缓存的类 
     * 继承Thread线程类 
     */  
    private static class ClearCache extends Thread{
    	public ClearCache() {
		}
    	
    	@Override
    	public void run(){  
    		while(!isInterrupted()){  
    			Set<String> tempSet = new HashSet()<String>();  
    			Set<String> set = cacheConfMap.keySet();  
    			Iterator<String> it = set.iterator();  
    			while(it.hasNext()){  
    				String key = it.next();
    				String obj = cacheMap.get(key);
    				CacheConfModel ccm = (CacheConfModel)cacheConfMap.get(key);
    				if (obj.equals("exit")) {
    					ClearAll();
						return ;
					}
    				//比较是否需要清除  
    				if(!ccm.isForever()){  
    					if((new Date().getTime()-ccm.getBeginTime())>= ccm.getDurableTime()*60*1000){
    						tempSet.add(key);  //可以清除，先记录下来 
    					}
    				}
    			}
    			//真正清除  
    			Iterator<String> tempIt = tempSet.iterator();  
    			while(tempIt.hasNext()){  
    				String key = tempIt.next();  
    				cacheMap.remove(key);  
    				cacheConfMap.remove(key);
    			}
    			//休息  
    			try {  
    				Thread.sleep(60*1000L);  
    			} catch (InterruptedException e) {  
    				System.out.println("exit cache-Thread");
    				break;
    			}
    		}
    		ClearAll();
    	}
    	private static void ClearAll() {
			cacheConfMap.clear();
			cacheMap.clear();
		}
    	
    }
}
