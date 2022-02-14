package mindustry.content;

import arc.struct.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;

import static mindustry.content.Blocks.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.*;

public class ErekirTechTree{

    public static void load(){
        //TODO might be unnecessary with no asteroids
        Seq<Objective> erekirSector = Seq.with(new OnPlanet(Planets.erekir));

        var costMultipliers = new ObjectFloatMap<Item>();
        costMultipliers.put(Items.silicon, 9);
        costMultipliers.put(Items.surgeAlloy, 4);
        costMultipliers.put(Items.phaseFabric, 4);
        costMultipliers.put(Items.thorium, 9);
        costMultipliers.put(Items.graphite, 9);
        //oxide is hard to make
        costMultipliers.put(Items.oxide, 0.5f);

        //TODO remove
        Objective tmpNever = new Research(Items.fissileMatter);

        //TODO gate behind capture

        Planets.erekir.techTree = nodeRoot("erekir", coreBastion, true, () -> {
            context().researchCostMultipliers = costMultipliers;

            node(duct, erekirSector, () -> {
                node(ductRouter, () -> {
                    node(ductBridge, () -> {
                        node(surgeConveyor, () -> {
                            node(surgeRouter);
                        });

                        node(unitCargoLoader, () -> {
                            node(unitCargoUnloadPoint, () -> {

                            });
                        });
                    });

                    node(overflowDuct, () -> {
                        node(reinforcedContainer, () -> {
                            node(ductUnloader, () -> {

                            });

                            node(reinforcedVault, () -> {

                            });
                        });
                    });
                });

                node(reinforcedPayloadConveyor, Seq.with(new OnSector(four)), () -> {
                    //TODO should only be unlocked in unit sector
                    node(constructor, Seq.with(new Research(siliconArcFurnace), new OnSector(four)), () -> {

                        node(payloadMassDriver, () -> {
                            //TODO further limitations
                            node(payloadLoader, () -> {
                                node(payloadUnloader, () -> {
                                    node(payloadPropulsionTower, () -> {

                                    });
                                });
                            });

                            node(smallDeconstructor, () -> {
                                node(largeConstructor, () -> {

                                });

                                node(deconstructor, () -> {

                                });
                            });
                        });
                    });

                    node(reinforcedPayloadRouter, () -> {

                    });
                });
            });

            //TODO move into turbine condenser?
            node(plasmaBore, () -> {
                node(impactDrill, Seq.with(new OnSector(aware)), () -> {
                    node(largePlasmaBore, Seq.with(new OnSector(four)), () -> {
                        node(eruptionDrill, () -> {

                        });
                    });
                });
            });

            node(turbineCondenser, () -> {
                node(beamNode, () -> {
                    node(ventCondenser, Seq.with(new OnSector(aware)), () -> {
                        node(chemicalCombustionChamber, Seq.with(new OnSector(three)), () -> {
                            node(pyrolysisGenerator, () -> {

                            });
                        });
                    });

                    node(beamTower, Seq.with(new OnSector(four)), () -> {

                    });


                    node(regenProjector, () -> {
                        //TODO more tiers of build tower or "support" structures like overdrive projectors
                        node(buildTower, Seq.with(new OnSector(four)), () -> {

                        });
                    });
                });

                node(reinforcedConduit, () -> {
                    //TODO maybe should be even later
                    node(reinforcedPump, Seq.with(new OnSector(three)), () -> {
                        //TODO T2 pump, consume cyanogen or similar
                    });

                    node(reinforcedLiquidJunction, () -> {
                        node(reinforcedBridgeConduit, () -> {

                        });

                        node(reinforcedLiquidRouter, () -> {
                            node(reinforcedLiquidContainer, () -> {
                                node(reinforcedLiquidTank, Seq.with(new SectorComplete(three)), () -> {

                                });
                            });
                        });
                    });
                });

                node(siliconArcFurnace, () -> {
                    node(cliffCrusher, () -> {
                        node(electrolyzer, Seq.with(new OnSector(three)), () -> {
                            node(oxidationChamber, () -> {
                                node(electricHeater, Seq.with(new OnSector(four)), () -> {
                                    node(heatRedirector, () -> {

                                    });

                                    node(atmosphericConcentrator, Seq.with(new OnSector(four)), () -> {
                                        node(cyanogenSynthesizer, () -> {

                                        });
                                    });

                                    node(carbideCrucible, Seq.with(tmpNever), () -> {
                                        node(surgeCrucible, () -> {
                                            node(phaseSynthesizer, () -> {
                                                node(phaseHeater, () -> {

                                                });
                                            });
                                        });
                                    });
                                });
                            });

                            node(slagIncinerator, Seq.with(new OnSector(four)), () -> {

                                node(slagCentrifuge, () -> {

                                });

                                node(heatReactor, () -> {

                                });
                            });
                        });
                    });
                });
            });

            node(breach, Seq.with(new Research(siliconArcFurnace)), () -> {
                node(berylliumWall, () -> {
                    node(berylliumWallLarge, () -> {

                    });

                    node(tungstenWall, () -> {
                        node(tungstenWallLarge, () -> {

                        });
                    });
                });


                node(sublimate, () -> {
                    //TODO implement
                    node(titan, () -> {

                    });

                    node(disperse, () -> {

                    });
                });
            });

            node(coreCitadel, Seq.with(new SectorComplete(four)), () -> {
                node(coreAcropolis, () -> {

                });
            });

            node(tankAssembler, Seq.with(new OnSector(four), new Research(constructor), new Research(atmosphericConcentrator)), () -> {
                node(UnitTypes.vanquish, () -> {
                    node(UnitTypes.conquer, Seq.with(tmpNever), () -> {

                    });
                });

                node(shipAssembler, () -> {
                    node(UnitTypes.quell, () -> {
                        node(UnitTypes.disrupt, Seq.with(tmpNever), () -> {

                        });
                    });

                    node(mechAssembler, Seq.with(tmpNever), () -> {
                        node(UnitTypes.bulwark, () -> {
                            node(UnitTypes.krepost, Seq.with(tmpNever), () -> {

                            });
                        });
                    });
                });
            });

            //TODO more sectors
            node(onset, () -> {
                node(aware, Seq.with(new SectorComplete(onset), new Research(ductRouter)), () -> {
                    node(three, Seq.with(new SectorComplete(aware), new Research(reinforcedContainer), new Research(ductUnloader), new Research(ventCondenser)), () -> {
                        node(four, Seq.with(new SectorComplete(three), new Research(electrolyzer), new Research(oxidationChamber), new Research(chemicalCombustionChamber)), () -> {

                        });
                    });
                });
            });

            nodeProduce(Items.beryllium, () -> {
                nodeProduce(Items.oxide, () -> {
                    nodeProduce(Items.fissileMatter, () -> {

                    });
                });

                nodeProduce(Liquids.ozone, () -> {
                    nodeProduce(Liquids.hydrogen, () -> {
                        nodeProduce(Liquids.nitrogen, () -> {
                            nodeProduce(Liquids.cyanogen, () -> {

                            });
                        });
                    });
                });

                nodeProduce(Items.tungsten, () -> {
                    nodeProduce(Items.carbide, () -> {
                        nodeProduce(Liquids.gallium, () -> {

                        });
                    });
                });
            });
        });
    }
}
