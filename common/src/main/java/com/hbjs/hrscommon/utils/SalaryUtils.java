package com.hbjs.hrscommon.utils;

import com.hbjs.hrscommon.dto.SalaryDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryUtils {

    public static void clearSalaryValue(SalaryDTO salaryDTO) {
        salaryDTO
                .setSalarySubtotal(BigDecimal.ZERO)
                .setBasicSalary(BigDecimal.ZERO)
                .setPostSalary(BigDecimal.ZERO)
                .setMeritSalary(BigDecimal.ZERO)
                .setSickSalary(BigDecimal.ZERO)
                .setBackSalary(BigDecimal.ZERO)
                .setOvertimeSalary(BigDecimal.ZERO)
                .setBonusSubtotal(BigDecimal.ZERO)
                .setAnnualBonus(BigDecimal.ZERO)
                .setSafetyBonus(BigDecimal.ZERO)
                .setStabilityBonus(BigDecimal.ZERO)
                .setFamilyPlanningBonus(BigDecimal.ZERO)
                .setExcellenceBonus(BigDecimal.ZERO)
                .setSpecialBonus(BigDecimal.ZERO)
                .setAllowanceSubtotal(BigDecimal.ZERO)
                .setOneChildAllowance(BigDecimal.ZERO)
                .setHotWeatherAllowance(BigDecimal.ZERO)
                .setFullAttendanceAllowance(BigDecimal.ZERO)
                .setNightShiftAllowance(BigDecimal.ZERO)
                .setOnDutyAllowance(BigDecimal.ZERO)
                .setMealAllowance(BigDecimal.ZERO)
                .setTrafficAllowance(BigDecimal.ZERO)
                .setFestivalAllowance(BigDecimal.ZERO)
                .setSafetyAllowance(BigDecimal.ZERO)
                .setCommunicationAllowance(BigDecimal.ZERO)
                .setOtherAllowance(BigDecimal.ZERO)
                .setPreTaxDeductSubtotal(BigDecimal.ZERO)
                .setSickLeaveDeduct(BigDecimal.ZERO)
                .setEntryExitDeduct(BigDecimal.ZERO)
                .setFullAttendanceDeduct(BigDecimal.ZERO)
                .setMeritDeduct(BigDecimal.ZERO)
                .setMaterialSubtotal(BigDecimal.ZERO)
                .setBirthdayCard(BigDecimal.ZERO)
                .setCoolDrink(BigDecimal.ZERO)
                .setCondolenceGoods(BigDecimal.ZERO)
                .setAftTaxDeductSubtotal(BigDecimal.ZERO)
                .setAccumulationFund(BigDecimal.ZERO)
                .setEndowmentInsurance(BigDecimal.ZERO)
                .setUnemploymentInsurance(BigDecimal.ZERO)
                .setMedicalInsurance(BigDecimal.ZERO)
                .setUnionFees(BigDecimal.ZERO)
                .setRent(BigDecimal.ZERO)
                .setPhoneBill(BigDecimal.ZERO)
                .setIndividualIncomeTax(BigDecimal.ZERO)
                .setOtherAftTaxDeduct(BigDecimal.ZERO)
                .setShouldSalary(BigDecimal.ZERO)
                .setSalarySubtotal(BigDecimal.ZERO)
                .setPreTaxSalary(BigDecimal.ZERO)
                .setActualSalary(BigDecimal.ZERO);
    }

    public static void calculationSalaryTotal(SalaryDTO salaryDTO) {
        salaryDTO
                .setSalarySubtotal(
                        salaryDTO.getBasicSalary()
                                .add(salaryDTO.getPostSalary())
                                .add(salaryDTO.getMeritSalary())
                                .add(salaryDTO.getSickSalary())
                                .add(salaryDTO.getBackSalary())
                                .add(salaryDTO.getOvertimeSalary())
                                .setScale(2, RoundingMode.HALF_UP))
                .setBonusSubtotal(
                        salaryDTO.getAnnualBonus()
                                .add(salaryDTO.getSafetyBonus())
                                .add(salaryDTO.getStabilityBonus())
                                .add(salaryDTO.getFamilyPlanningBonus())
                                .add(salaryDTO.getExcellenceBonus())
                                .add(salaryDTO.getSpecialBonus())
                                .setScale(2, RoundingMode.HALF_UP))
                .setAllowanceSubtotal(
                        salaryDTO.getOneChildAllowance()
                                .add(salaryDTO.getHotWeatherAllowance())
                                .add(salaryDTO.getFullAttendanceAllowance())
                                .add(salaryDTO.getNightShiftAllowance())
                                .add(salaryDTO.getOnDutyAllowance())
                                .add(salaryDTO.getMealAllowance())
                                .add(salaryDTO.getTrafficAllowance())
                                .add(salaryDTO.getFestivalAllowance())
                                .add(salaryDTO.getSafetyAllowance())
                                .add(salaryDTO.getCommunicationAllowance())
                                .add(salaryDTO.getOtherAllowance())
                                .setScale(2, RoundingMode.HALF_UP))
                .setPreTaxDeductSubtotal(
                        salaryDTO.getSickLeaveDeduct()
                                .add(salaryDTO.getEntryExitDeduct())
                                .add(salaryDTO.getFullAttendanceDeduct())
                                .add(salaryDTO.getMeritDeduct())
                                .setScale(2, RoundingMode.HALF_UP))
                .setMaterialSubtotal(
                        salaryDTO.getBirthdayCard()
                                .add(salaryDTO.getCoolDrink())
                                .add(salaryDTO.getCondolenceGoods())
                                .setScale(2, RoundingMode.HALF_UP))
                .setAftTaxDeductSubtotal(
                        salaryDTO.getAccumulationFund()
                                .add(salaryDTO.getEndowmentInsurance())
                                .add(salaryDTO.getUnemploymentInsurance())
                                .add(salaryDTO.getMedicalInsurance())
                                .add(salaryDTO.getUnionFees())
                                .add(salaryDTO.getRent())
                                .add(salaryDTO.getPhoneBill())
                                .add(salaryDTO.getIndividualIncomeTax())
                                .add(salaryDTO.getOtherAftTaxDeduct())
                                .setScale(2, RoundingMode.HALF_UP))
                .setShouldSalary(
                        salaryDTO.getSalarySubtotal()
                                .add(salaryDTO.getBonusSubtotal())
                                .add(salaryDTO.getAllowanceSubtotal())
                                .subtract(salaryDTO.getPreTaxDeductSubtotal())
                                .setScale(2, RoundingMode.HALF_UP))
                .setPreTaxSalary(
                        salaryDTO.getShouldSalary()
                                .add(salaryDTO.getMaterialSubtotal())
                                .subtract(salaryDTO.getOneChildAllowance())
                                .setScale(2, RoundingMode.HALF_UP))
                .setActualSalary(
                        salaryDTO.getShouldSalary()
                                .subtract(salaryDTO.getAftTaxDeductSubtotal())
                                .setScale(2, RoundingMode.HALF_UP));
    }


}