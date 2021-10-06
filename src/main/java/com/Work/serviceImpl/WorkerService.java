package com.Work.serviceImpl;

import com.Work.daoImpl.DetailDao;
import com.Work.daoImpl.WorkerDao;
import com.Work.entity.DetailEntity;
import com.Work.entity.WorkerEntity;
import com.Work.model.dto.WorkerDto;
import com.Work.service.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService implements IWorkerService {

    @Autowired
    private WorkerDao workerDao;

    @Autowired
    private DetailDao detailDao;

    @Override
    public List<WorkerEntity> getAllList() {
        return workerDao.getAllList();
    }

    @Override
    public WorkerEntity createOrUpdate(WorkerDto workerDto) {
        WorkerEntity workerEntity=null;
        if(workerDto.getId()==null){
            workerEntity=new WorkerEntity();
        }else{
            workerEntity=workerDao.getById(workerDto.getId());
        }
        workerEntity.setFirstName(workerDto.getFirstName());
        workerEntity.setLastName(workerDto.getLastName());
        workerEntity.setEmail(workerDto.getEmail());
        workerEntity.setTelNumber(workerDto.getTelNumber());
        workerEntity.setPassword(workerDto.getPassword());
        workerEntity.setAge(workerDto.getAge());

        if(workerDto.getDetail() != null){
            DetailEntity detailEntity = detailDao.getById(workerDto.getDetail());
            workerEntity.setDetail(detailEntity);
        }

        return workerDao.createOrUpdate(workerEntity);
    }

    @Override
    public String deleteById(Long id) {
        int result=workerDao.deleteById(id);
        return result==1?"başarılı":"başarısız" ;
    }

    @Override
    public WorkerEntity getById(Long id) {
        return workerDao.getById(id);
    }

    @Override
    public List<WorkerEntity> search(String search) {
        return workerDao.search(search);
    }
}
