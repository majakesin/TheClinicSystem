package ftn.project.services_impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ftn.project.dto.DtoEntity;
import ftn.project.dto.PrescriptionDto;
import ftn.project.mapper.DtoUtils;
import ftn.project.model.Prescription;
import ftn.project.repository.PrescriptionRepository;
import ftn.project.services.PrescriptionService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrescriptionServiceImpl implements PrescriptionService {

	private final PrescriptionRepository prescriptionRepository;

	private final UserService userService;
	
	private final DtoUtils mapper;

	@Override
	public Set<DtoEntity> getAllPrescriptions() {
		List<Prescription> prescriptionList = prescriptionRepository.findAll();
		return prescriptionList.stream().map(p -> mapper.convertToDto(p, new PrescriptionDto()))
				.collect(Collectors.toSet());
	}

	@Override
	public void certifiedPrescription(Long idPrescription) {
		Prescription prescription = prescriptionRepository.findById(idPrescription).get();
		prescription.setCertified(true);
		prescriptionRepository.save(prescription);
	}

	@Override
	public void createPrescription(DtoEntity dto) {
		prescriptionRepository.save((Prescription) mapper.convertToEntity(new Prescription(), dto));
	}

	@Override
	public Set<DtoEntity> getAllPrescriptionsByPacientId(Long id) {
		List<Prescription> list = prescriptionRepository.findAllByPacientIdAndCertified(id,false);
		Set<DtoEntity> entity=new HashSet<DtoEntity>();
		for(Prescription p: list) {
			entity.add(mapper.convertToDto(p, new PrescriptionDto()));
		}
		return entity;
	}

	@Override
	public Set<DtoEntity> getAllPrescriptionsByCertified(boolean certified) {
		List<Prescription> list = prescriptionRepository.findAllByCertified(certified);
		Set<DtoEntity> entity=new HashSet<DtoEntity>();
		for(Prescription p: list) {
			entity.add(mapper.convertToDto(p, new PrescriptionDto()));
		}
		return entity;

	}

	@Override
	public void finishedExamination(Long pacientId) {
		List<Prescription> list = prescriptionRepository.findAllByPacientIdAndCertified(pacientId,false);
		list.forEach(l -> l.setExamination(true));
		prescriptionRepository.saveAll(list);

	}

	@Override
	public void deletePrescription(Long id) {
		prescriptionRepository.deleteById(id);

	}

}
