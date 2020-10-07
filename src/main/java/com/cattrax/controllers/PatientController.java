package com.cattrax.controllers;

import com.cattrax.domain.Patient;
import com.cattrax.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Dell User on 4/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */

@Controller
public class PatientController {
    @Autowired
    PatientService patientService;

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public String listPatients(Model model){
        model.addAttribute("patients", patientService.listPatients());
        return "patients";
    }

    @RequestMapping (value = "/patient/{id}")
    public String showPatient(@PathVariable Integer patientId, Model model){
        model.addAttribute("patient", patientService.getPatientById(patientId));
        return "showPatient";
    }

    @RequestMapping("/patient/delete/{id}")
    public String delete(@PathVariable Integer patientId){
        patientService.deletePatient(patientId);
        return "redirect:/patients";
    }

    @RequestMapping("patient/edit/{id}")
    public String editPatient(@PathVariable Integer patientId, Model model){
        model.addAttribute("patient", patientService.getPatientById(patientId));
        return "patientForm";
    }
    @RequestMapping("patient/register")
    public String registerPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "patientForm";
    }
    // when the form is submitted to save or update patient, this method is called from the form action url:
    // it redirects to call the above controller method: showPatient which maps the URL: /patient/{patientId}
    @RequestMapping(value = "/savePatient", method = RequestMethod.POST)
    public String saveOrUpdatePatient(Patient patient){
        Patient savedPatient = patientService.registerOrEditPatient(patient);
        return "redirect:/patient/" + savedPatient.getId();
    }

    @RequestMapping (value = "/patient/search/{patientNHI}", method = RequestMethod.POST)
    public String searchPatientByNhi(@PathVariable String patientNHI, Model model){
        model.addAttribute("patient", patientService.searchPatientByNhi(patientNHI));
        return "showPatient";
    }
}