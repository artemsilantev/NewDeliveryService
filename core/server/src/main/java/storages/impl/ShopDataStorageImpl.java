package storages.impl;

import filemanagers.FileManager;
import handlers.Handler;
import java.util.Collection;
import mappers.Mapper;
import model.Shop;
import storages.ShopDataStorage;
import validators.Validator;

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