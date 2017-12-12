package money.funds.controller;

import org.json.JSONException;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import money.funds.service.MoneyFundsService;

/**
 * <p>
 * <b> money funds controller class </b>
 * </p>
 */
@RestController
@CrossOrigin
@RequestMapping({"/api/money/funds/market", "/api/v1.1"}) // Standard
public class MoneyFundsController {
    @Autowired
    private MoneyFundsService moneyFundsService;

    /**
     *
     * <p>
     * <b> get money Funds data </b>
     * </p>
     *
     * @return
     * @throws SystemException
     * @throws JSONException 
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMoneyFundsData() throws SystemException {
       return this.moneyFundsService.processMoneyFundsData();
    }
}
