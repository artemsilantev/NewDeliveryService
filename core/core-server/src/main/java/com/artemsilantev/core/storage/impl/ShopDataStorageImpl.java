package com.artemsilantev.core.storage.impl;

import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.core.handler.Handler;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.storage.ShopDataStorage;
import com.artemsilantev.core.validator.Validator;
import java.util.Collection;

public class ShopDataStorageImpl extends BaseDataStorageImpl<Shop>
    implements ShopDataStorage {


  public ShopDataStorageImpl(
      Handler<Mapper<Shop, String>, String> mapperHandler,
      FileManager fileManager, String pathToFile,
      Collection<Validator<String>> textValidators,
      Collection<Validator<Shop>> entityValidators) {
    super(mapperHandler, fileManager, pathToFile, textValidators, entityValidators);
  }
}