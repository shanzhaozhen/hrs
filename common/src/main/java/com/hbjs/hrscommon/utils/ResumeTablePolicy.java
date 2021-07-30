package com.hbjs.hrscommon.utils;

import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.hbjs.hrscommon.dto.*;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResumeTablePolicy extends DynamicTableRenderPolicy {

    @Override
    public void render(XWPFTable table, Object data) throws Exception {
        if (null == data) return;

        ResumeMoreInfo resumeMoreInfo = (ResumeMoreInfo) data;

        int workStartRow = 17;
        int educationalStartRow = 28;
        int certificateRow = 23;
        int familyStartRow = 34;

        Style cellStyle = new Style();
        cellStyle.setFontFamily("宋体");
        cellStyle.setFontSize(9);

        List<WorkExperienceDTO> workExperienceDTOList = resumeMoreInfo.getWorkExperienceList();
        if (!CollectionUtils.isEmpty(workExperienceDTOList)) {
            for (int i = 0; i < 5 && i < workExperienceDTOList.size(); i++) {
                WorkExperienceDTO workExperienceDTO = workExperienceDTOList.get(i);
                RowRenderData row = new RowRenderData();

                String value = DateUtils.format(workExperienceDTO.getStartDate(), "yyyy年MM月") + "\n" +
                        DateUtils.format(workExperienceDTO.getEndDate(), "yyyy年MM月");
                row.addCell(Cells.of(new TextRenderData(value, cellStyle)).create());
                row.addCell(Cells.create(workExperienceDTO.getWorkCompany()));
                row.addCell(Cells.create(workExperienceDTO.getDuty()));
                row.addCell(Cells.create(workExperienceDTO.getCompanyType()));
                row.addCell(Cells.create(workExperienceDTO.getSalary().toString()));
                row.addCell(Cells.create(""));
                row.addCell(Cells.create(workExperienceDTO.getWitnessName()));
                row.addCell(Cells.create(workExperienceDTO.getWitnessPhone()));
                TableRenderPolicy.Helper.renderRow(table.getRow(workStartRow++), row);
            }
        }

        List<CertificateDTO> certificateDTOS = resumeMoreInfo.getCertificateList();
        if (!CollectionUtils.isEmpty(certificateDTOS)) {
            Map<String, List<CertificateDTO>> collect = certificateDTOS.stream().collect(Collectors.groupingBy(item -> "驾驶证".equals(item.getName()) ? "driver" : "other", Collectors.toList()));
            List<CertificateDTO> other = collect.get("other");
            List<CertificateDTO> driver = collect.get("driver");
            for (int i = 0; i < 3; i++) {
                List<String> cells = new ArrayList<>();
                fillCertificate(other, i, cells);
                fillCertificate(driver, i, cells);
                RowRenderData row = Rows.of(cells.toArray(new String[0])).center().create();
                TableRenderPolicy.Helper.renderRow(table.getRow(certificateRow++), row);
            }
        }

        List<EducationalExperienceDTO> educationalExperienceList = resumeMoreInfo.getEducationalExperienceList();
        if (!CollectionUtils.isEmpty(educationalExperienceList)) {
            for (int i = 0; i < 4 && i < educationalExperienceList.size(); i++) {
                EducationalExperienceDTO educational = educationalExperienceList.get(i);
                List<String> cells = new ArrayList<>();
                cells.add(DateUtils.format(educational.getStartDate(), "yyyy年MM月") + "~"+
                        DateUtils.format(educational.getEndDate(), "yyyy年MM月"));
                cells.add(educational.getSchoolName());
                cells.add(educational.getEducation());
                cells.add(educational.getMajor());
                cells.add(educational.getStudyYears().toString());
                cells.add("全日制".equals(educational.getStyle()) ? "是" : "否");
                cells.add(educational.getWitnessName());
                RowRenderData row = Rows.of(cells.toArray(new String[0])).center().create();
                TableRenderPolicy.Helper.renderRow(table.getRow(educationalStartRow++), row);
            }
        }

        List<FamilyDTO> familyList = resumeMoreInfo.getFamilyList();
        if (!CollectionUtils.isEmpty(familyList)) {
            for (int i = 0; i < 8 && i < familyList.size(); i++) {
                FamilyDTO familyDTO = familyList.get(i);
                List<String> cells = new ArrayList<>();
                cells.add(familyDTO.getName());
                cells.add(familyDTO.getDuty());
                cells.add(DateUtils.format(familyDTO.getBirthday(), "yyyy年MM月"));
                cells.add(familyDTO.getPolitics());
                cells.add(familyDTO.getWorkCompany());
                cells.add(familyDTO.getDuty());
                cells.add("固话：" + familyDTO.getLandlinePhone() + "\n" +
                            "手机：" + familyDTO.getMobilePhone());
                RowRenderData row = Rows.of(cells.toArray(new String[0])).center().create();
                TableRenderPolicy.Helper.renderRow(table.getRow(familyStartRow++), row);
            }
        }

//        XWPFTableRow insertNewTableRow = table.insertNewTableRow(workStartRow);

    }

    private void fillCertificate(List<CertificateDTO> certificateDTOList, int index, List<String> cells) {
        if (certificateDTOList.size() > index + 1) {
            CertificateDTO certificateDTO = certificateDTOList.get(index);
            cells.add(certificateDTO.getName());
            cells.add(DateUtils.format(certificateDTO.getObtainDate(), "yyyy年MM月"));
        } else {
            for (int j = 0; j < 3; j++) cells.add("");
        }
    }

}
