package edu.ivan.decl.parser;

import edu.ivan.decl.entity.Contract;
import edu.ivan.decl.entity.ITN;
import edu.ivan.decl.entity.Transport;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransportParser {

    public List<Transport> createTransports(List<List<String>> data){
        List<Transport> result = new ArrayList<>();

        for(List<String> temp : data){
            Transport transport = new Transport();
            Contract contract = new Contract();
            String actDate = temp.get(0);
            transport.setActDate(actDate);

            String number = temp.get(1);
            LocalDate contractDate = LocalDate.parse(temp.get(2));
            contract.setNumber(number);
            contract.setDate(contractDate);
            transport.setContract(contract);

            String route = temp.get(3);
            transport.setRoute(route);
            Double sum = Double.parseDouble(temp.get(4));
            transport.setProfit(sum);

            String ItnNumber = temp.get(5);
            LocalDate ItnDate = LocalDate.parse(temp.get(6));
            transport.setItn(new ITN(ItnDate,ItnNumber));

            result.add(transport);
        }
        return result;
    }

}
