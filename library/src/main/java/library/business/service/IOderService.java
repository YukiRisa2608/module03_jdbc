package library.business.service;

import library.business.model.Oder;
import library.business.model.OderDto;

import java.util.List;

public interface IOderService {
    List<OderDto> findAll(int displayOption);
    void save(Oder oder);
    OderDto getOrderDetails(Oder oder);
    OderDto getOrderById(Long order_id);

}
