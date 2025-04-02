package com.indiafirstpandit.response;

import com.indiafirstpandit.dto.ProductDto;
import com.indiafirstpandit.dto.PujaDto;
import com.indiafirstpandit.dto.samagri.SamagriDto;
import com.indiafirstpandit.model.Product;
import com.indiafirstpandit.model.Puja;
import com.indiafirstpandit.model.Samagri;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchResults {
    private List<PujaDto> pujas;
    private List<ProductDto> products;
    private List<SamagriDto> samagris;
}
