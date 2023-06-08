package com.example.pojo.query;

import com.example.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-07
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "Student 分页参数", description = "")
public class StudentQuery extends BasePageQuery {


}
