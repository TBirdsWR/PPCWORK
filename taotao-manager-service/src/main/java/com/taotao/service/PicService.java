package com.taotao.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PicService {

	Map upLoadPicture(MultipartFile file);
}
