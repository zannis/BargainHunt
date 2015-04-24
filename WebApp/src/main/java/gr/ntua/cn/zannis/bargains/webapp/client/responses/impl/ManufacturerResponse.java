package gr.ntua.cn.zannis.bargains.webapp.client.responses.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import gr.ntua.cn.zannis.bargains.webapp.client.responses.meta.Meta;
import gr.ntua.cn.zannis.bargains.webapp.persistence.entities.Manufacturer;

import java.util.List;

/**
 * The wrapper class for {@link Manufacturer} related
 * requests.
 *
 * @author zannis <zannis.kal@gmail.com
 */
public class ManufacturerResponse extends RestResponseImpl<Manufacturer> {

    @JsonCreator
    public ManufacturerResponse(@JsonProperty("manufacturer") Manufacturer manufacturer,
                                @JsonProperty("manufacturers") List<Manufacturer> manufacturers,
                                @JsonProperty("meta") Meta meta,
                                @JsonProperty("error") String errorMessage) {
        this.item = manufacturer;
        this.items = manufacturers;
        this.meta = meta;
        this.errorMessage = errorMessage;
    }
}