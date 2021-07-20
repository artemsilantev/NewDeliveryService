package com.artemsilantev.core.storages.impl;

import com.artemsilantev.core.filemanagers.FileManager;
import com.artemsilantev.core.handlers.Handler;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.storages.ShopDataStorage;
import com.artemsilantev.core.validators.Validator;
import java.util.Collection;

public class ShopDataStorageImpl extends AbstractDataStorageImpl<Shop>
    implements ShopDataStorage {


  public ShopDataStorageImpl(
      Handler<Mapper<Shop, String>, String> mapperHandler,
      FileManager fileManager, String pathToFile,
      Collection<Validator<String>> textValidators,
      Collection<Validator<Shop>> entityValidators) {
    super(mapperHandler, fileManager, pathToFile, textValidators, entityValidators);
  }
}