package com.yahtzee.core;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:features",
                 glue="com.yahtzee.core",
                 plugin = {"pretty"})
public class RunCucumberTest {}
