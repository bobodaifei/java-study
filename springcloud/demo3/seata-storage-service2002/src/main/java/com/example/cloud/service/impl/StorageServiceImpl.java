package com.example.cloud.service.impl;

import com.example.cloud.dao.StorageDao;
import com.example.cloud.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    public static final Logger LOGGER = (Logger) LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    //扣减库存
    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("----------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        LOGGER.info("----------->storage-service中扣减库存结束");
    }
}

