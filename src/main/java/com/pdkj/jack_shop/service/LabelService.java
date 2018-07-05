package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.model.Label;
import org.springframework.stereotype.Service;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class LabelService extends BaseService<Label> {

    public int addLabel(Label label) {
        return labelDao.addLabel(label);
    }


}
