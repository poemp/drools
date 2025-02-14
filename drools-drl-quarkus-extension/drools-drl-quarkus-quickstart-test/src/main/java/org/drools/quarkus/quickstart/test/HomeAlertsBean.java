/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.quarkus.quickstart.test;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.quarkus.quickstart.test.model.Alert;
import org.drools.quarkus.quickstart.test.model.CCTV;
import org.drools.quarkus.quickstart.test.model.Light;
import org.drools.quarkus.quickstart.test.model.Smartphone;
import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.RuleUnitInstance;

@ApplicationScoped
public class HomeAlertsBean {
	
    @Inject
    RuleUnit<HomeRuleUnitData> ruleUnit;
    
    public Collection<Alert> computeAlerts(Collection<Light> lights, Collection<CCTV> cameras, Collection<Smartphone> phones) {
        HomeRuleUnitData homeUnitData = new HomeRuleUnitData();
        lights.forEach(homeUnitData.getLights()::add);
        cameras.forEach(homeUnitData.getCctvs()::add);
        phones.forEach(homeUnitData.getSmartphones()::add);

        try (final RuleUnitInstance<HomeRuleUnitData> unitInstance = ruleUnit.createInstance(homeUnitData)) {
            var queryResults = unitInstance.executeQuery("All Alerts");
            return queryResults.toList().stream()
                    .flatMap(m -> m.values().stream()
                            .filter(Alert.class::isInstance)
                            .map(Alert.class::cast))
                    .collect(Collectors.toList());
        }
    }
}
