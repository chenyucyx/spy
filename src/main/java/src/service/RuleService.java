package src.service;

import org.springframework.stereotype.Service;
import src.model.RuleDefinition;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by chenyu on 2017/7/3.
 */
@Service
public class RuleService {

    public List<RuleDefinition> queryAllRules() {
        return Stream.of(RuleDefinition.values()).collect(Collectors.toList());
    }

}
