package com.informes_predicacion.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.informes_predicacion.org.classes.ReportOfPredicationReports;
import com.informes_predicacion.org.classes.TerritoryDetailProgress;
import com.informes_predicacion.org.dtos.req.CreateReportDto;
import com.informes_predicacion.org.dtos.req.GetSchedulesQueryParamsDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
import com.informes_predicacion.org.dtos.res.ReportDto;
import com.informes_predicacion.org.dtos.res.ScheduleDto;
import com.informes_predicacion.org.dtos.res.UserDto;
import com.informes_predicacion.org.entities.Block;
import com.informes_predicacion.org.entities.Report;
import com.informes_predicacion.org.entities.Territory;
import com.informes_predicacion.org.mappers.IScheduleMapper;
import com.informes_predicacion.org.mappers.IUserMapper;
import com.informes_predicacion.org.mappers.ReportMapper;
import com.informes_predicacion.org.repositories.IReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {
  private final IReportRepository reportRepository;
  private final ReportMapper reportMapper;
  private final IScheduleService scheduleService;
  private final IUserService userService;
  private final ITerritoryService territoryService;
  private final IBlockService blockService;
  private final IScheduleMapper scheduleMapper;
  private final IUserMapper userMapper; 
  
  @Override
  public ListResponseDto<ReportDto> findAllByCongregationId(Long congregationId, GetSchedulesQueryParamsDto queryParams) {
    Page<Report> reportsResult = reportRepository.findAllByCongregationId(congregationId, queryParams.toPageable());
    return ListResponseDto.from(reportsResult, reportMapper); 
  }

  @Override
  public ReportDto findByIdAndCongregationId(Long id, Long congregationId) {
    Optional<Report> report = reportRepository.findByIdAndCongregationId(id, congregationId);
    if (!report.isPresent()) {
      throw new RuntimeException("Report not found");
    }
    return reportMapper.toDto(report.get());
  }

  @Override
  public Object createReport(CreateReportDto dto, Long congregationId) {
    Report report = reportMapper.toEntity(dto);
    report = getAndVerifyRelations(report, congregationId);
    try {
      report = reportRepository.save(report);
      return reportMapper.toDto(report);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public void deleteReportByIdAndCongregationId(Long id, Long congregationId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteReportByIdAndCongregationId'");
  }

  @Override
  public Report getAndVerifyRelations(Report entity, Long congregationId) {
    
    ScheduleDto schedule = scheduleService.findByIdAndCongregationId(entity.getSchedule().getId(), congregationId);
    entity.setSchedule(scheduleMapper.toEntity(schedule));
    UserDto userDto = userService.findByIdAndCongregationId(entity.getPreachingDriver().getId(), congregationId);
    entity.setPreachingDriver(userMapper.toEntity(userDto));

    Boolean existItems = entity.getItems() != null && !entity.getItems().isEmpty();
    
    if (existItems) {
      Set<Long> territoryIds = entity.getItems().stream().map(item -> item.getTerritory().getId()).collect(Collectors.toSet());
      List<Territory> territories = territoryService.findByManyIdsAndCongregationId(territoryIds, congregationId);

      entity.getItems().forEach(item -> {
        Territory territory = territories.stream().filter(t -> t.getId().equals(item.getTerritory().getId())).findFirst().get();
        item.setTerritory(territory);

        Boolean existBlocks = item.getBlocks() != null && !item.getBlocks().isEmpty();
        System.out.println("existBlocks: " + existBlocks);
        if (existBlocks) {
          List<Long> blockIds = item.getBlocks().stream().map(reportTerritoryBlockItem -> reportTerritoryBlockItem.getBlock().getId()).collect(Collectors.toList());
          List<Block> blocks = blockService.findManyByIdsAndTerritoryId(blockIds, territory.getId());
          item.getBlocks().forEach(reportTerritoryBlockItem -> {
            Block block = blocks.stream().filter(b -> b.getId().equals(reportTerritoryBlockItem.getBlock().getId())).findFirst().get();
            reportTerritoryBlockItem.setBlock(block);
          });
        }
      });

    }
    return entity;
  }

  @Override
  public ReportOfPredicationReports getReportOfPredicationReports(String startDate, String endDate,
      Long congregationId) {
    List<Report> reports = new ArrayList<>();
    ReportOfPredicationReports reportOfPredicationReports = new ReportOfPredicationReports();
    reportOfPredicationReports.setStartDate(startDate);
    reportOfPredicationReports.setEndDate(endDate);
    
    startTerritoryRecords(reportOfPredicationReports, reports);
    List<Territory> territories = new ArrayList<>();
    territories.forEach(territory -> {
      List<Report> reportsByTerritory = getReportsByTerritoryId(reports, territory.getId());
      orderReportsByDateAsc(reportsByTerritory);
      TerritoryDetailProgress territoryDetailProgress = new TerritoryDetailProgress();
      reportsByTerritory.forEach(report -> {
        if (territoryDetailProgress.getInitialDate() == null) {
          territoryDetailProgress.setInitialDate(report.getDate());
        }
        if (territoryDetailProgress.getPreachingDriverOpenderCompleteName() == null) {
          territoryDetailProgress.setPreachingDriverOpenderCompleteName(report.getPreachingDriver().getNames() + " " + report.getPreachingDriver().getLastNames());
        }

      });

    });
    // reports.forEach(report -> {
    //   String date = report.getDate();
    //   String preachingDriverCompleteNames = report.getPreachingDriver().getNames() + " " + report.getPreachingDriver().getLastNames();
    //   report.getItems().forEach(reportTerritoryItem -> {
    //     String territoryName = reportTerritoryItem.getTerritory().getName();
    //   });
    // });
    return null;
  }
  private void startTerritoryRecords(ReportOfPredicationReports reportOfPredicationReports, List<Report> reports) {
    reports.forEach(report -> {
      report.getItems().forEach(reportTerritoryItem -> {
        String territoryName = reportTerritoryItem.getTerritory().getName();
        if(reportOfPredicationReports.getDetails().get(territoryName) == null) {
          reportOfPredicationReports.getDetails().put(territoryName, new ArrayList<TerritoryDetailProgress>());
        }
      });
    });
  }
  private List<Report> getReportsByTerritoryId(List<Report> reports, Long territoryId) {
    return reports.stream().filter(report -> report.getItems().stream().anyMatch(item -> item.getTerritory().getId().equals(territoryId))).collect(Collectors.toList());
  }
  private void orderReportsByDateAsc(List<Report> reports) {
    reports.sort((r1, r2) -> r1.getDate().compareTo(r2.getDate()));
  }
  
  
}
