package org.dishes.controller;

import java.util.List;

import javax.inject.Inject;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.BoardFacade;
import org.dishes.facade.command.CreateBoardCommand;
import org.dishes.facade.dto.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 餐桌控制器
 */
@Controller
@RequestMapping(value = "/board")
public class BoardController {
	@Inject
	private BoardFacade boardFacade;
	
	/**
	 * 根据id获取餐桌
	 */
	@ResponseBody
	@RequestMapping(value = "/get",method = RequestMethod.POST)
	public InvokeResult<BoardDTO> get(String boardId){
		return boardFacade.getBoardById(boardId);
	}
	/**
	 * 删除餐桌
	 */
	@ResponseBody
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public InvokeResult<String> delete(String boardId){
		return boardFacade.deleteBoardById(boardId);
	}
	/**
	 * 添加餐桌列表
	 */
	@ResponseBody
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public InvokeResult<String> add(CreateBoardCommand command){
		return boardFacade.addBoard(command);
	}
	
	/**
	 * 餐桌列表
	 */
	@ResponseBody
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	public InvokeResult<List<BoardDTO>> list(){
		return boardFacade.listBoard();
	}
}
